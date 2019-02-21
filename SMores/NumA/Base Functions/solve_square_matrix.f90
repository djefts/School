program solve_matrix
    IMPLICIT NONE
    integer i, j, k, n
    doubleprecision m, sum
    doubleprecision a(4, 4), x(4)

    !    n = 4
    !    a(1, :) = [  1, 1, 0, 3, 4 ]
    !    a(2, :) = [  2, 1, -1, 1, 1 ]
    !    a(3, :) = [  3, -1, -1, 2, -3 ]
    !    a(4, :) = [ -1, 2, 3, -1, 4 ]
    !    a(5, :) = [  0, 0, 0, 0, 0 ]

    !(/ 1,  1,  0,  3,  4, 0, &
    !  2,  1, -1,  1,  1, 0, &
    ! 3, -1, -1,  2, -3, 0, &
    !-1,  2,  3, -1,  4, 0/)

    !Lab 4 Matrix:
    n = 4
    a(1, :) = [  1, -1, 3, -3 ]
    a(2, :) = [ -1, 0, -2, 1 ]
    a(3, :) = [  2, 2, 4, 0 ]
    a(4, :) = [  0, 0, 0, 0 ]

    write(*, *) print_matrix(a, n)
    write(*, *) ""
    !partial_pivot(r, c, a, n)
    !call partial_pivot(1, 1, 1, a, n)
    !write(*, *) print_matrix(a, n)
    stop

    !Forward Elimination
    do k = 1, n - 1
        do i = k + 1, n
            m = a(i, k) / a(k, k)
            do j = k + 1, n
                a(i, j) = a(i, j) - (m * a(k, j))
            ENDDO
        ENDDO
    ENDDO

    !Backward Substitution
    do i = n - 1, 1, -1
        sum = a(i, n + 1)
        do j = i + 1, n
            sum = sum - a(i, j) - x(j)
        end do
        x(i) = sum / a(i, i)
    ENDDO

    do i = 1, n
        write(*, '(a)') "x", i, "=", x(i)
    end do

CONTAINS

    SUBROUTINE partial_pivot(r, c, k, a, n)
        IMPLICIT NONE
        INTEGER :: n, k, r, c
        DOUBLEPRECISION, DIMENSION(n, n) :: a(n, n)
        integer row, col, i
        doubleprecision largest, temp

        !find row with largest column
        row = -1
        largest = 0
        DO i = k, n
            IF(a(i, c) > largest) THEN
                largest = abs(a(i, c))
                row = i
            END IF
        END DO

        !swap the two rows
        DO j = k, n
            temp = a(c, j)
            a(c, j) = a(k, j)
            a(k, j) = temp
        END DO

    END SUBROUTINE partial_pivot

    INTEGER FUNCTION print_matrix(a, n)
        IMPLICIT NONE
        INTEGER :: n
        DOUBLEPRECISION, DIMENSION(n, n) :: a(n, n)
        integer i, j
        character*100 output, num

        write(*, *) "Printing the matrix..."

        !        output = ""
        !        DO i = 1, n
        !
        !            output = output // "["
        !            DO j = 1, n
        !                write(num, "(A5,I2)") a(i, j)
        !                output = output // num // ", "
        !            END DO
        !            output = output // "]"
        !        END DO
        !        write(*, *) output
        !        print_matrix = 0

        do i = 1, n
            write(*, "(100g15.5)") (a(i, j), j = 1, n)
        enddo
    END FUNCTION print_matrix
END PROGRAM solve_matrix