program bisection
    implicit none
    double precision a, b, x_o, x_m, err, tol
    integer n

    !INITIALIZE VARIABLES
    a = 1
    b = 2
    tol = 10.0**(-6)
    n = int(log((b - a) / tol) / log(2.0))

    if(f(a) * f(b) > 0) then
        stop
    end if

    x_m = a + b
    do n = 1, 10000
        x_o = x_m
        x_m = (a + b) / 2
        !write(*, *) "points:", a, x_m, b
        if(abs(f(x_m)) < tol) then
            write(*, *) "SOLVED IT!!!", x_m
            stop
        end if
        if(f(a) * f(x_m) < 0) then
            b = x_m
        end if
        if(f(a) * f(x_m) > 0) then
            a = x_m
        end if
        err = abs((x_o - x_m) / x_o)
        !write(*, *) "err = ", err
    end do

contains

    double precision FUNCTION f(x)
        IMPLICIT NONE
        doubleprecision :: x
        f = x**3 + x**2 - 3 * x - 3
    END FUNCTION f

end program bisection