!------------------------------------------------------------------------------------!
	PROGRAM Lab1
! Purpose:
! Evaluate F(x) = a - b*(x**3) at N+1 equidistant points in [0,1],
! and output the pairs x , F(x) on the screenand in file fort.7
! for plotting.
!____________________________________________________________________________________
	IMPLICIT NONE
! Declare the variables used in this program
	Double precision a,b
	integer N,i
	Double precision Dx,xi,Fi

! Get input from user:
	write(*,*),'Please enter values for a, b, N :'
	read(*,*),'Thanks, will run  with:'
	write(*,*),' a=',a,' , b=',b,' , N=',N
	write(*,*),''
	write(*,*),' x F(x)'
	write(7,*)'# Output from Lab1 with: a=',a,' b=',b,' N=',N
c Compute F(x) = a - b*(x**3) and print x F(x):
	Dx = 1.0 / N
	DO i = 0, N
	xi  = i * Dx
	  Fi =  a -b * xi**3
	write(*,*), xi, Fi
	write(7,*) xi, Fi
	ENDDO
! Exit:
	write(*,*),'All  Done,  BYE !'

	END
