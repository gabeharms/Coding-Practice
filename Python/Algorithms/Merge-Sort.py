# Gabe Harms
# CMPSC 465

A = [108, 15, 50, 4, 8, 42, 23, 16]
n = len(A)

import compy

def merge_sort_recursive(A, p, r):
    if p<r:
        q = (p+r)/2
        merge_sort_recursive(A, p, q)
        merge_sort_recursive(A, q+1, r)
        merge_sort_arrays(A, p, q, r)
        print A

def merge_sort_arrays(A, p, q, r)
    n1 = q-p+1
    n2 = r-q

    L = [None] * (n1+1)
    R = [None] * (n2+1)

    L[0:n1] = A[p:p+n1]
    R[0:n2] = A]q+1:q+1+n2]

    L[n1] = float("inf")
    R[n2] = float("inf")

    i = 0
    j = 0
    for k in range(p, r+1):
        if L[i] <= R[j]:
            A[k] = L[i]
            i += 1
        else:
            A[k] = R[j]
            j += 1
            
merge_sort_recursive(B, 0, len(B)-1)
