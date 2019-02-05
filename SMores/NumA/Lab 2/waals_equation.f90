program waals_equation
    implicit none
    double precision R, a, b, true
    integer n, P, T
    double precision x_o, x_n, tol, err, r1, r2
    double precision step, func

    !INITIALIZE VARIABLES
    R = 0.082054    !atm/(mol*K)
    a = 1.360
    b = 0.03183
    P = 10          !atm
    T = 300         !K

    ! Do not know if this is that actual true value. This is just as exact as
    !!!! Fortran can get
    true = 2.4384037628086563

    !fortran can print out up to 16 decimal places so this will produce a
    !!!!value as exact as possible with fortran
    tol = 10.0**(-16)   !tolerance
    r1 = 2.0            !left solution range value
    r2 = 2.5            !right solution range value
    x_n = (r1 + r2)     !initial estimate

    !possible P and T values
    !P = 1, 10, 100
    !T = 300, 500, 700

    !record basic function values from v = [2.0, 2.5]
    do n = 1, 200
        !function:
        !waals(x_n, R, a, b, P, T)

        !!plot f(v)
        step = 0.5 + (n * 0.01)
        func = waals(step, R, a, b, P, T)
        write(*, *) n, step, func
        !write(7, *) step, func
    end do

!    temp : do T = 300, 500, 700
!        pressure : do P = 1, 10, 100
            !estimate the roots, maximum of 500 iterations
            estimate : do n = 1, 500
                !function:
                !waals(x_n, R, a, b, P, T)

                x_o = x_n

                !!!bisection method
                !x_n = (r1 + r2) / 2

                !!!false position method
                x_n = (waals(r2, R, a, b, P, T) * r1) - (waals(r1, R, a, b, P, T) * r2)
                x_n = x_n / (waals(r2, R, a, b, P, T) - waals(r1, R, a, b, P, T))

                !calculate error and print
                err = abs(x_o - x_n) !error
                write(*, *) n, err, x_n

                !!!!!!! Print all the things !!!!!!!
                !!plot error vs time
                !write(7, *) n, err  !bisection
                write(8, *) n, err  !false pos

                !determine if error is small "enough"
                if(err < tol .and. n > 1) then
                    write(*, *) "What I found: ", x_n
                    !do not know the true value
                    !write(*, *) "Actual value: ", true
                    write(*, *) n, " iterations"
                    exit
                end if

                if(waals(r1, R, a, b, P, T) * waals(x_n, R, a, b, P, T) < 0) then
                    r2 = x_n
                end if
                if(waals(r1, R, a, b, P, T) * waals(x_n, R, a, b, P, T) > 0) then
                    r1 = x_n
                end if
            end do estimate
!        end do pressure
!    end do temp

    CALL SYSTEM('gnuplot script.sh')

contains

    double precision FUNCTION waals(V, R, a, b, P, T)
        IMPLICIT NONE
        doubleprecision :: R, a, b, V
        integer ::  P, T
        waals = P * V**3 - (b * P + R * T) * V**2 + a * V - a * b
    END FUNCTION waals

end program waals_equation