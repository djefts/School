import numpy as np
import copy

#x=float(input('guess'))
def ff(x):
    aa=(x[0])**3-2*(x[1])-2
    bb=(x[0])**3-5*(x[2])**2+7
    cc=(x[1])*(x[2])**2-1
    fo=np.array([[aa],[bb],[cc]])
    return fo

def df(x): #jacobian
    a=3*(x[0])**2
    b=3*(x[0])**2
    c=-10*(x[2])
    d=(x[2])**2
    e=2*x[1]*(x[2])
    b=np.array([[a,-2,0],[b,0,c], [0,d,e]])
    return b

def Guass(A):
    #L=
    n = len(A)
    #print(n)
        #scaling
        # max=A[0,0]
        # for i in range(n+1):
        #     for j in range (n):
        #         if A[j,i]>max:
        #             max=A[j,i]
        # for i in range(n+1):
        #     for j in range (n):
        #
        #         A[j,i]=A[j,i]/max
#end scale

    #print(A)
    for col in range (n): #Goes column by column
            # for i in range(col,n):#goes through each element under pivot position
            #     max=abs(A[col,col])
            #     if abs(A[i,col])>max:#puts columns under pivot in descending order
            #         max=abs(A[i,col])
            #         c=A[i]
            #         f=copy.deepcopy(c)
            #
            #         A[i]=A[col]
            #         A[col]=f
            #
            #         print(A)
            #
            #     else:
            #      e=1

        for j in range(col+1,n):#finds factor to multiply pivot row by
                # print(A[j,col])
                # print(A[col,col])
            a = (A[j,col]) / (A[col,col])
                # L[j,col]=a

            for jj in range(col, n+1):
                w=A[col,jj]

                b=A[j,jj]
                bb=copy.deepcopy(b)

                z=a * w
                zz=copy.deepcopy(z)



                c=(bb-  zz)
                cc=copy.deepcopy(c)

                A[j,jj] =bb-zz
            #print(A)

    #print(A)
    x=np.zeros(n)
    x[n-1]=A[n-1,n]/A[n-1,n-1]#sets last value of x


    for t in range(n-1,-1,-1):#backsub
        l=0#resets l for each row
        for j in range(t+1,n):#eliminates other variables in row from rhs
            l = l  + (A[t,j])*x[j]

        x[t]=(A[t,n] - l)/A[t,t]
        #print(x)
    #print('X=')
    print(x)
    return x


guess=np.array([1,2,3])
#print(dydx(guess))
for n in range(10):
    print('round',n)
    print(guess)

    j=df(guess)

    #print(j)
    f=ff(guess)
    #print(f)
    a=np.hstack((j,-f))
    #print(a)
    h=Guass(a)
    print('h',h)
    new=h+guess

    if abs(new[0]-guess[0])<.00001:
        if abs(new[1]-guess[1])<.00001:
            if abs(new[2]-guess[2])<.00001:
                print('tol')
                break
    guess=h+guess
    x=ff(guess)

    print('x',(guess))
    print('fx',ff(guess))

    #print('jac',dydx(guess))
