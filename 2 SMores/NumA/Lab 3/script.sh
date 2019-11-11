#!/usr/bin/gnuplot

set title "P=100 and T=700"

set xzeroaxis linestyle 8
set xtics axis 1
set xlabel "Number Iterations"
set ylabel "Modal Volume"
plot "fort.7" title "newton's method" with linespoints, "fort.8" title "ideal gas law" with linespoints

### PLOT 4 GRAPHS AT ONCE ###
#plot [0:] "fort.7" title "newton's" lw 2 with linespoints, \
#    "fort.8" title "secant" lw 2 with linespoints, \
#    "fort.9" title "mod secant" lw 2 with linespoints, \
#    "fort.10" title "mod newton's" lw 2 with linespoints, \

#set table "plot.tex"
#plot "fort.7"
#unset table

pause -1