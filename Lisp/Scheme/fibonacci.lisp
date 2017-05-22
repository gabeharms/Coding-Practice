; tree recursive
(define (tree-fib n)
    (cond ((= n 0) 0)
           ((= n 1) 1)
           (else (+ (fib (- n 1)) 
                    (fib (- n 2))))))

; iteravive recursive
(define (iterative-fib n)
  (define (fib-iter sum count max)
      (if (= count max)
        sum
        (fib-iter (+ count (+ count 1))
                  (+ count 1) 
                  max)))
  (fib-iter 1 1 n))
