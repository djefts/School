!------------------------------------------------------------------------------------!
PROGRAM Errors
    ! Purpose:
    ! Evaluate F(x) = a - b*(x**3) at N+1 equidistant points in [0,1],
    ! and output the pairs x , F(x) on the screenand in file fort.7
    ! for plotting.
    ! (-0.1)*x**4-0.15*x**3-0.5*x**2-0.25*x+1.2
    !____________________________________________________________________________________
    IMPLICIT NONE
    ! Declare the variables used in this program
    double precision x, dx, val, true, error
    integer i

    x = 0.5
    true = (-0.9125)
    ! Compute F(x) = a - b*(x**3) and print x F(x):
    dx = 0.1
    DO i = 1, 20
        val = (base(x + dx) - base(x - dx)) / (2 * dx)
        error = Abs((val - true) / true)

        write(*, *) ''
        write(*, *) '        dx: ', dx
        write(*, *) '     error: ', error
        write(*, *) '   log(dx): ', log10(dx)
        write(*, *) 'log(error): ', log10(error)
        write(7, *) log10(dx), error
        dx = dx/10
    ENDDO
    ! Exit:
    write(*, *) 'All  Done,  BYE !'

CONTAINS

    doubleprecision FUNCTION base(x)
        IMPLICIT NONE
        doubleprecision :: x
        base = (-0.1) * x**4 - 0.15 * x**3 - 0.5 * x**2 - 0.25 * x + 1.2
    END FUNCTION base

END PROGRAM errors