program bisection
    implicit none
    double precision a, b, x_o, x_m, err, tol, true
    integer n, count

    !INITIALIZE VARIABLES
    a = 1
    b = 2
    tol = 10.0**(-6)
    n = int(log((b - a) / tol) / log(2.0))
    true = 1.7320508
    count = 0

    if(f(a) * f(b) > 0) then
        stop
    end if

    x_m = a + b
    do n = 1, 10000
        count = count + 1
        x_o = x_m

        !!!bisection method
        x_m = (a + b) / 2

        !!!false position method
        x_m = (f(b) * a - f(a) * b) / (f(b) - f(a))

        !write(*, *) "points:", a, x_m, b
        if(abs(f(x_m)) < tol) then
            write(*, *) "SOLVED IT!!!", x_m
            write(*, *) "Actual value: ", true
            write(*, *) count, " iterations"
            stop
        end if
        if(f(a) * f(x_m) < 0) then
            b = x_m
        end if
        if(f(a) * f(x_m) > 0) then
            a = x_m
        end if
        err = abs((x_o - x_m) / x_o)
        write(7, *) count, err
    end do

contains

    double precision FUNCTION f(x)
        IMPLICIT NONE
        doubleprecision :: x
        f = x**3 + x**2 - 3 * x - 3
    END FUNCTION f

end program bisection