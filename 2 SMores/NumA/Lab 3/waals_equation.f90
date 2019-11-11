program waals_equation
    implicit none
    doubleprecision R, a, b, true
    integer n, P, T
    doubleprecision x_oo, x_o, x_n, tol, err, r1, r2, delta
    doubleprecision step, func
    doubleprecision x_ioo, x_io, x_i, diff

    !INITIALIZE VARIABLES
    R = 0.082054    !atm/(mol*K)
    a = 1.360
    b = 0.03183
    P = 10          !atm
    T = 300         !K

    ! Do not know if this is that actual true value. This is just as exact as
    !!!! Fortran can get
    !!!Temp = 300
    !true = 24.592799
    true = 2.438403
    !true = 0.226358
    !!!Temp = 500
    !true = 41.025704
    !true = 4.101629
    !true = 0.411614
    !!!Temp = 700
    !true = 57.445966
    !true = 5.752097
    !true = 0.584196

    tol = 10.0**(-6)   !tolerance

    !initial estimate
    x_n =  3
    x_o =  4
    x_oo = 5
    delta = .01

    !possible P and T values
    !P = 1, 10, 100
    !T = 300, 500, 700
    P = 10; T = 300
    !estimate the roots, maximum of 500 iterations
    estimate : do n = 1, 1000
        !function:
        !waals(x_n, R, a, b, P, T)
        !ideal(r2, R, P, T) * r1)

        !!!Newton's Method
        x_n = x_o - (waals(x_o, R, a, b, P, T) / dwaals(x_o, R, a, b, P, T))
        err = abs(x_o - x_n) !approximate error
        x_o = x_n
        x_oo = x_o

        !!!Secant Method
        !x_n = x_o - ((waals(x_o, R, a, b, P, T) * (x_o - x_oo)) / (waals(x_o, R, a, b, P, T) - waals(x_oo, R, a, b, P, T)))
        !x_oo = x_o
        !x_o = x_n

        !!!Modified Secant Method
        !x_n = x_o - ((waals(x_o, R, a, b, P, T) * delta) / (waals(x_o + delta, R, a, b, P, T) - waals(x_o, R, a, b, P, T)))
        !x_o = x_n

        !!!Modified Newton's Method for Roots of Multiplicity
        !x_n = (waals(x_o, R, a, b, P, T) * dwaals(x_o, R, a, b, P, T))
        !x_n = x_n / ((dwaals(x_o, R, a, b, P, T))**2 - waals(x_o, R, a, b, P, T) * ddwaals(x_o, R, a, b, P, T))
        !x_n = x_o - x_n
        !x_o = x_n

        !ideal gas law  values
        x_i = ideal(x_i, R, P, T)

        write(*, *) n, x_n, err
        write(7, *) n, x_n
        write(8, *) n, x_i

        !determine if error is small "enough"
        if(err < tol .and. n > 1) then
            write(*, *) "What I found: ", x_n
            !do not know the true value
            !write(*, *) "Actual value: ", true
            write(*, *) n, " iterations"
            exit
        end if
    end do estimate

    CALL SYSTEM('gnuplot script.sh')

contains

    !van der Waal's equation of state
    double precision FUNCTION waals(V, R, a, b, P, T)
        IMPLICIT NONE
        doubleprecision :: R, a, b, V
        integer :: P, T
        waals = P * V**3 - (b * P + R * T) * V**2 + a * V - a * b
    END FUNCTION waals

    double precision FUNCTION dwaals(V, R, a, b, P, T)
        IMPLICIT NONE
        doubleprecision :: R, a, b, V
        integer :: P, T
        dwaals = 3 * P * V**2 - 2 * (b * P + R * T) * V + a
    END FUNCTION dwaals

    double precision FUNCTION ddwaals(V, R, a, b, P, T)
        IMPLICIT NONE
        doubleprecision :: R, a, b, V
        integer :: P, T
        ddwaals = 6 * P * V - 2 * (b * P + R * T)
    END FUNCTION ddwaals

    !Ideal Gas Law
    double precision FUNCTION ideal(V, R, P, T)
        IMPLICIT NONE
        doubleprecision :: R, V
        integer :: P, T
        ideal = (R * T) / P
    END FUNCTION ideal

end program waals_equation