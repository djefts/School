program estimate_fx
    implicit none
    double precision a, b, err, tol, true
    doubleprecision x_n, x_o, x_oo, delta
    integer n
    logical method

    !INITIALIZE VARIABLES
    a = 1
    b = 2
    tol = 10.0**(-6)
    n = int(log((b - a) / tol) / log(2.0))
    true = 1.7320508
    method = .false.

    x_oo = 3.0
    x_o = 1.0
    do n = 1, 1000
        !!!Bisection Method
        !x_n = (a + b) / 2
        !x_o = x_n
        !method = .true.

        !!!False Position Method
        !x_n = (f(b) * a - f(a) * b) / (f(b) - f(a))
        !x_o = x_n
        !method = .true.

        !!!Fixed Point Method
        !x_n = g(x_o)
        !x_o = x_n

        !!!Newton's Method
        x_n = x_o - (f(x_o) / deriv(x_o))
        x_o = x_n

        !!!Secant Method
        !x_n = x_o - ((f(x_o) * (x_o - x_oo)) / (f(x_o) - f(x_oo)))
        !x_oo = x_o
        !x_o = x_n

        !!!Modified Secant Method
        !x_n = x_o - ((f(x_o) * delta) / (f(x_o + delta) - f(x_o)))
        !x_o = x_n

        !!!Modified Newton's Method for Roots of Multiplicity
        !x_n = x_o - ((f(x_o) * deriv(x_o)) / ((deriv(x_o))**2 - f(x_o) * dderiv(x_o)))
        !x_o = x_n

        !err = abs(x_n - x_o)    !relative error
        err = abs(true - x_n)   !true error
        write(*, *) n, x_n, err
        !write(7, *) n, err

        if(err < tol) then
            write(*, *) "What I found: ", x_n
            write(*, *) "Actual value: ", true
            write(*, *) n, " iterations"
            exit
        end if

        !only used for bisection and false position methods
        if(method) then
            if(f(a) * f(b) > 0) then
                write(*, *) "Can't solve."
                stop
            endif
            if(f(a) * f(x_n) < 0) then
                b = x_n
            elseif(f(a) * f(x_n) > 0) then
                a = x_n
            end if
        end if
    end do

    CALL SYSTEM('gnuplot -p script.sh')

contains

    double precision FUNCTION f(x)
        IMPLICIT NONE
        doubleprecision :: x
        f = x**3 + x**2 - 3 * x - 3
        !f = x**3 - 5 * x**2 + 7 * x - 3
    END FUNCTION f

    double precision FUNCTION g(x)
        IMPLICIT NONE
        doubleprecision :: x
        doubleprecision third
        third = 1.0 / 3.0
        g = ((x**2 - 3.0 * x - 3) * (-1)) ** (1.0 / 3.0)
    END FUNCTION g

    doubleprecision FUNCTION deriv(x)
        IMPLICIT NONE
        doubleprecision :: x
        !deriv = 2 * x**2 + 2 * x - 3
        deriv = 3 * x**2 - 10 * x + 7
    END FUNCTION deriv

    doubleprecision FUNCTION dderiv(x)
        IMPLICIT NONE
        doubleprecision :: x
        !dderiv = 6 * x + 2
        dderiv = 6 * x - 10
    END FUNCTION dderiv

end program estimate_fx