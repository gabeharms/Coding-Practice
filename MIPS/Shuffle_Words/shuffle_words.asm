# CMPEN 331, Homework 4

# switch to the Data segment
	.data
	# global data is defined here
	# Don't forget the backslash-n (newline character)
Homework:
	.asciiz	"CMPEN 331 Homework 4\n"
Name_1:
	.asciiz "Gabe Harms\n"
Name_2:
	.asciiz "Jimmy Trinh\n"

# switch to the Text segment
	.text
	# the program is defined here
	.globl	main
main:
	# Whose program is this?
	la	$a0, Homework
	jal	Print_string
	la	$a0, Name_1
	jal	Print_string
	la	$a0, Name_2
	jal	Print_string
	
	la	$a0, cr
	jal	Print_string
	la	$a0, header
	jal	Print_string
	
	# int i, n;
	# for (i = 0; i < 18; i++)
	#   {
	#      ... calculate n from i
	#      ... print i and n
	#   }
	
	# register assignments
	#	$s0	i
	#	$s1	n
	#	$s2	address of testcase[0]
	#	$s3	testcase[i]
	#	$t0	temporary values
	#	$a0	argument to Print_integer, Print_string, etc.
	#	add to this list if you use any other registers
	
	la	$s2, testcase
	
	# for (i = 0; i < 18; i++)
	li	$s0, 0		# i = 0
	bge	$s0, 18, bottom
top:
	# calculate n from shuffle32(testcase[i])
	sll	$t0, $s0, 2	# 4*i
	add	$t0, $s2, $t0	# address of testcase[i]
	lw	$s3, 0($t0)	# testcase[i]
	
	move	$a0, $s3
	jal	shuffle32
	move	$s1, $v0	# n = shuffle32(testcase[i])
	
	# print i and n
	# if (i < 10) print an extra space for alignment
	bge	$s0, 10, L1
	la	$a0, sp		# space
	jal	Print_string
L1:
	move	$a0, $s0	# i
	jal	Print_integer
	la	$a0, sp		# space
	jal	Print_string
	move	$a0, $s3	# testcase[i]
	jal	Print_hex
	la	$a0, sp		# space
	jal	Print_string
	move	$a0, $s3	# testcase[i]
	jal	Print_binary
	la	$a0, sp		# space
	jal	Print_string
	move	$a0, $s1	# n
	jal	Print_binary
	la	$a0, sp		# space
	jal	Print_string
	move	$a0, $s1	# n
	jal	Print_hex
	la	$a0, cr		# newline
	jal	Print_string
	
	# for (i = 0; i < 18; i++)
	add	$s0, $s0, 1	# i++
	blt	$s0, 18, top	# i < 18
bottom:
	
	la	$a0, done	# mark the end of the program
	jal	Print_string
	
	jal	Exit	# end the program, no explicit return status

	
# switch to the Data segment
	.data
	# global data is defined here
sp:
	.asciiz	" "
cr:
	.asciiz	"\n"
done:
	.asciiz	"All done!\n"
header:
	.asciiz	" i testcase[i]           testcase[i] in binary        shuffled result in binary     result\n"

testcase:
	.word	0xffffffff,	#  0
		0xffff0000,	#  1
		0x0000ffff,	#  2
		0xff00ff00,	#  3
		0x00ff00ff,	#  4
		0xf0f0f0f0,	#  5
		0x0f0f0f0f,	#  6
		0xcccccccc,	#  7
		0x33333333,	#  8
		0xaaaaaaaa,	#  9
		0x55555555,	# 10
		0x00000000,	# 11
		0xffff0000,	# 12
		0xaaaaaaaa,	# 13
		0xcccccccc,	# 14
		0xf0f0f0f0,	# 15
		0xff00ff00,	# 16
		0x12345678	# 17
		

# Wrapper functions around some of the system calls
# See P&H COD, Fig. B.9.1, for the complete list.  More are available with MARS.

# switch to the Text segment
	.text

	.globl	Print_integer
Print_integer:	# print the integer in register a0, decimal
	li	$v0, 1
	syscall
	jr	$ra

	.globl	Print_hex
Print_hex:	# print the integer in register a0, hexadecimal
	li	$v0, 34
	syscall
	jr	$ra

	.globl	Print_binary
Print_binary:	# print the integer in register a0, binary
	li	$v0, 35
	syscall
	jr	$ra

	.globl	Print_string
Print_string:	# print the string whose starting address is in register a0
	li	$v0, 4
	syscall
	jr	$ra

	.globl	Exit
Exit:		# end the program, no explicit return status
	li	$v0, 10
	syscall
	jr	$ra

	.globl	Exit2
Exit2:		# end the program, with return status from register a0
	li	$v0, 17
	syscall
	jr	$ra

# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

# Your part starts here
shuffle32:
	addi $sp, $sp, -4 #increasing the stack by one 
	sw $ra, 0($sp) #storing the return address into the stack
	
	srl  $t0, $a0, 24 #first group of 8
	move $t9, $t0 
	
	sll  $t0, $a0, 8  #second group of 8
	srl  $t0, $t0, 24 
	move $t8, $t0
	
	sll  $t0, $a0, 16 #third group of 8
	srl  $t0, $t0, 24
	move $t7, $t0
	
	sll  $t0, $a0, 24 #fourth group of 8
	srl  $t0, $t0, 24
	move $t6, $t0
	
	sll $t9, $t9, 8 #first group of 16
	add $t9, $t9, $t7
	
	sll $t8, $t8, 8 #second group of 16
	add $t8, $t8, $t6
	
	move $a0, $t9
	jal shuffle16
	move $t9, $v0
	
	move $a0, $t8
	jal shuffle16
	move $t8, $v0
	
	sll $t9, $t9, 16
	add $v0, $t9, $t8
	
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra
	
shuffle16:
	addi $sp, $sp, -4 #increasing the stack by one 
	sw $ra, 0($sp) #storing the return address into the stack
	
	srl $t0, $a0, 12 # first group of 4
	
	sll $t1, $a0, 20 # second group of 4
	srl $t1, $t1, 28
	
	sll $t2, $a0, 24 #third group of 4
	srl $t2, $t2, 28
	
	sll $t3, $a0, 28 #fourth group of 4
	srl $t3, $t3, 28
	
	sll $t0, $t0, 4 #first group of 8
	add $t7, $t0, $t2
	
	sll $t1, $t1, 4 #second group of 8
	add $t6, $t1, $t3

	move $a0, $t7
	jal shuffle8
	move $t7, $v0
	
	move $a0, $t6
	jal shuffle8
	move $t6, $v0
	
	sll $t7, $t7, 8
	add $v0, $t7, $t6
	
	lw $ra, 0($sp) #pops the stack and returns to the return address
	addi $sp, $sp, 4
	jr $ra

shuffle8:
	addi $sp, $sp, -4 #increasing the stack by one 
	sw $ra, 0($sp) #storing the return address into the stack
	
	srl $t0, $a0, 6 # first group of 2
	
	sll $t1, $a0, 26 # second group of 2
	srl $t1, $t1, 30
	
	sll $t2, $a0, 28 #third group of 2
	srl $t2, $t2, 30
	
	sll $t3, $a0, 30 #fourth group of 2
	srl $t3, $t3, 30
	
	sll $t0, $t0, 2 #first group of 4
	add $t5, $t0, $t2
	
	sll $t1, $t1, 2 #second group of 4
	add $t4, $t1, $t3
	
	move $a0, $t5
	jal shuffle4
	move $t5, $v0
	
	move $a0, $t4
	jal shuffle4
	move $t4, $v0
	
	sll $t5, $t5, 4
	add $v0, $t5, $t4
	
	lw $ra, 0($sp) #pops the stack and returns to the return address
	addi $sp, $sp, 4
	jr $ra
	
shuffle4:
	addi $sp, $sp, -4 #increasing the stack by one 
	sw $ra, 0($sp) #storing the return address into the stack
	
	srl $t0, $a0, 3 # first group of 1
	
	sll $t1, $a0, 29 # second group of 1
	srl $t1, $t1, 31
	
	sll $t2, $a0, 30 #third group of 1
	srl $t2, $t2, 31
	
	sll $t3, $a0, 31 #fourth group of 1
	srl $t3, $t3, 31
	
	sll $t0, $t0, 1 #first group of 2
	add $t0, $t0, $t2
	
	sll $t1, $t1, 1 #second group of 2
	add $t1, $t1, $t3
	
	sll $t0, $t0, 2
	add $v0, $t0, $t1
	
	lw $ra, 0($sp) #pops the stack and returns to the return address
	addi $sp, $sp, 4
	jr $ra


# functions shuffle4, shuffle8, etc. are to be defined here

# Your part ends here

# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


