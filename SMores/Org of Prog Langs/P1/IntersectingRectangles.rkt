#lang racket
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;    (Intersections rects)    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; R# is rectangle with opposing corners at (x1, y1, x2,y2)
;; These are originally set to the given test cases
;; All four are already passed into the Intersections function
(define R1 (list 3 12 9 5))
(define R2 (list 7 7 12 2))
(define R3 (list 8 11 12 9))
(define R4 (list 12 5 16 3))
;; List of rectangles to be passed into (Intersections)
(define rects (list R1 R2 R3 R4))

;; Methods to get all four points in a rectangle
(define (getTop rectangle) ;y1
  (cadr rectangle)
)
(define (getBottom rectangle) ;y2
  (cadddr rectangle)
)
(define (getLeft rectangle) ;x1
  (car rectangle)
)
(define (getRight rectangle) ;x2
  (caddr rectangle)
)

;; Main Methods
(define (Intersections rectangles)
  (for ([i (in-range 0 (- (length rectangles) 1) 1)]) ;;loop through rectangles
    (for ([j (in-range 0 (- (length rectangles) 1) 1)]) ;;loop from first for +1 to the end
      (when (not (= i j))
        ;debugging- shows the rectangles currently being compared
        ;(displayln (list "checking: " (list-ref rectangles i) (list-ref rectangles j)))
        (checkIntersection (list-ref rectangles i) (list-ref rectangles j))
      )
    )
  )
)

(define (checkIntersection rect1 rect2)
  ;;define all 4 points of rect 1 to have shorter "when" statements
  (define topLeft (list (getLeft rect1) (getTop rect1)))
  (define topRight (list (getRight rect1) (getTop rect1)))
  (define bottomLeft (list (getLeft rect1) (getBottom rect1)))
  (define bottomRight (list (getRight rect1) (getBottom rect1)))
  ;;loop through all four points of rect1
  (for ([i (in-range 1 5 1)])
    (if (= i 1) ;;see if topLeft of rect1 is inside rect2
      (when (and (and (and (>= (car topLeft) (getLeft rect2)) (<= (car topLeft) (getRight rect2))) (>= (cadr topLeft) (getBottom rect2)) ) (<= (cadr topLeft) (getTop rect2))) (displayln (list rect1 rect2)))
      (if (= i 2) ;;see if topRight of rect1 is inside rect2
        (when (and (and (and (>= (car topRight) (getLeft rect2)) (<= (car topRight) (getRight rect2))) (>= (cadr topRight) (getBottom rect2)) ) (<= (cadr topRight) (getTop rect2))) (displayln (list rect1 rect2)))
        (if (= i 3) ;;see if bottomLeft of rect1 is inside rect2
          (when (and (and (and (>= (car bottomLeft) (getLeft rect2)) (<= (car bottomLeft) (getRight rect2))) (>= (cadr bottomLeft) (getBottom rect2)) ) (<= (cadr bottomLeft) (getTop rect2))) (displayln (list rect1 rect2)))
          (if (= i 4) ;;see if bottomRight of rect1 is inside rect2
            (when (and (and (and (>= (car bottomRight) (getLeft rect2)) (<= (car bottomRight) (getRight rect2))) (>= (cadr bottomRight) (getBottom rect2)) ) (<= (cadr bottomRight) (getTop rect2))) (displayln (list rect1 rect2)))
            (display "")
          )
        )
      )
    )
  )
)
