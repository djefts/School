!------------------------------------------------------------------------------------!
PROGRAM Errors
    ! Purpose:
    ! Evaluate F(x) = a - b*(x**3) at N+1 equidistant points in [0,1],
    ! and output the pairs x , F(x) on the screenand in file fort.7
    ! for plotting.
    ! (-0.1)*x**4-0.15*x**3-0.5*x**2-0.25*x+1.2
    !_________________________________________________________________________________
    IMPLICIT NONE
    ! Declare the variables used in this program
    double precision x, h, val, true, error
    integer i

    x = 0.5
    true = (-0.9125)
    ! Compute F(x) = a - b*(x**3) and print x F(x):
    h = 1.0
    DO i = 1, 11
        val = (base(x + h) - base(x - h)) / (2 * h)
        error = Abs((val - true))

        write(*, *) ''
        write(*, *) '     error: ', error
        write(*, *) ' step size: ', h
        write(*, *) '   log(dx): ', log10(h)
        write(*, *) 'log(error): ', log10(error)
        write(7, *) h, error
        h = h / 10
    ENDDO
    ! Exit:
    CALL SYSTEM('gnuplot -p script.sh')
    write(*, *) 'All  Done,  BYE !'

CONTAINS

    doubleprecision FUNCTION base(x)
        IMPLICIT NONE
        doubleprecision :: x
        base = (-0.1) * x**4 - 0.15 * x**3 - 0.5 * x**2 - 0.25 * x + 1.2
    END FUNCTION base

END PROGRAM errors