program estimate_fx
    implicit none
    double precision a, b, x_o, x_n, err, tol, true
    integer n, count
    logical method

    !INITIALIZE VARIABLES
    a = 1
    b = 2
    tol = 10.0**(-6)
    n = int(log((b - a) / tol) / log(2.0))
    true = 1.7320508
    count = 0
    method = .false.

    if(f(a) * f(b) > 0) then
        write(*, *) "Can't solve."
        stop
    end if

    x_n = a
    do n = 1, 500
        count = count + 1
        x_o = x_n

        !!!bisection method
        !x_n = (a + b) / 2
        method = .true.

        !!!false position method
        !x_n = (f(b) * a - f(a) * b) / (f(b) - f(a))
        !method = .true.

        !!!fixed point method
        x_n = g(x_n)

        err = abs(true - x_n)
        write(*, *) count, err, x_n, true
        write(7, *) count, err

        if(err < tol) then
            write(*, *) "What I found: ", x_n
            write(*, *) "Actual value: ", true
            write(*, *) count, " iterations"
            stop
        end if

        if(method) then
            if(f(a) * f(x_n) < 0) then
                b = x_n
            end if
            if(f(a) * f(x_n) > 0) then
                a = x_n
            end if
        end if

    end do

contains

    double precision FUNCTION f(x)
        IMPLICIT NONE
        doubleprecision :: x
        f = x**3 + x**2 - 3 * x - 3
    END FUNCTION f

    double precision FUNCTION g(x)
        IMPLICIT NONE
        doubleprecision :: x

        !f(x) = 0 --> g(x) = x
        !f(x) = x**3 + x**2 - 3*x - 3 = 0
        !add x to both sides
        !g = x**3 + x**2 - 2 * x - 3    ! diverges
        !!or possibly solve for x**1 term
        !g = (x**3 + x**2 - 3) / 3       ! diverges
        !!or possibly solve for x**2 term
        !g = ((-x)**3 + 3 * x + 3)**(0.5)
        !!or possibly solve for x**3 term
        g = (x**2 - 3 * x - 3)**(1 / 3)
    END FUNCTION g

end program estimate_fx