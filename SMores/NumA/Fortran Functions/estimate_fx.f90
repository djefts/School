program estimate_fx
    implicit none
    double precision a, b, x_o, x_n, err, err_o, tol, true
    integer n
    logical method

    !INITIALIZE VARIABLES
    a = 1
    b = 2
    tol = 10.0**(-6)
    n = int(log((b - a) / tol) / log(2.0))
    true = 1.7320508
    method = .false.

    if(f(a) * f(b) > 0) then
        write(*, *) "Can't solve."
        stop
    end if

    x_o = a
    x_n = b
    do n = 1, 500
        !!!bisection method
        !x_n = (a + b) / 2
        method = .true.

        !!!false position method
        !x_n = (f(b) * a - f(a) * b) / (f(b) - f(a))
        !method = .true.

        !!!Fixed Point method
        !!!DOES NOT WORK
        !x_n = g(x_o)

        !!!Newton's method
        !x_n = x_o - (f(x_o)/deriv(x_o))

        !!!Secant Method
        write(*, *) f(x_o), f(x_n)
        x_n = x_n - ((f(x_n) * (x_n - x_o)) / (f(x_n) - f(x_o)))

        err = abs(true - x_n)   !true error
        write(*, *) n, err, x_n
        write(7, *) n, err

        if(err < tol) then
            write(*, *) "What I found: ", x_n
            write(*, *) "Actual value: ", true
            write(*, *) n, " iterations"
            exit
        end if

        if(method) then
            if(f(a) * f(x_n) < 0) then
                b = x_n
            end if
            if(f(a) * f(x_n) > 0) then
                a = x_n
            end if
        end if
        x_o = x_n
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
        doubleprecision third
        g = (0 - x**2 + 2.0 * x + 3)
        third = 1.0 / 3
        g = g**third
    END FUNCTION g

    doubleprecision FUNCTION deriv(x)
        IMPLICIT NONE
        doubleprecision :: x
        deriv = 2 * x**2 + 2 * x - 3
    END FUNCTION deriv

end program estimate_fx