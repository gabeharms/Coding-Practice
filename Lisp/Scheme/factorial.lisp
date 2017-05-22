; linearlly recursive factorial
(define (linear-factorial n)
  (if (= n 1)
    1
    (* n (factorial (- n 1)))))


; iteratively recursive factorial
(define (iterative-factorial n)
  (define (fact-iter product counter max-count)
    (if (> counter max-count)
      product
      (fact-iter (* counter product)
                 (+ counter 1)
                 max-count)))
    (fact-iter 1 1 n))

; tail recursion is just the absence of special looping
; constructs in the languages syntax. both of these implementations
; are tail recursive
