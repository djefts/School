#!/usr/bin/gnuplot

set title "Graph of the function f(v)"

set xzeroaxis linestyle 8 linewidth 2
set xtics axis 0.1
set xlabel "v"
set ylabel "f(v)"
plot "fort.7" title "f(v)" with linespoints
#plot "fort.7" every ::1 title "bisection" with linespoints, "fort.8" every ::1 title "false pos" with linespoints

#set table "plot.tex"
#plot "fort.7"
#unset table

pause -1