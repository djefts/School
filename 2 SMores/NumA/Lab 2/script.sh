#!/usr/bin/gnuplot

set title "P=100 and T=700"

set xzeroaxis linestyle 8 linewidth 2
set xtics axis #0.1
set xlabel "Number Iterations"
set ylabel "Modal Volume"
#plot "fort.7" title "f(v)" with linespoints
#plot "fort.7" every ::1 title "bisection" with linespoints, "fort.8" every ::1 title "false pos" with linespoints
plot "fort.7" title "false pos" with linespoints, "fort.8" title "ideal gas" with linespoints

#set table "plot.tex"
#plot "fort.7"
#unset table

pause -1