#!/usr/bin/gnuplot

set xzeroaxis linestyle 8 linewidth 2
set xtics axis
set xlabel "v"
set ylabel "f(v)"
plot "fort.7" with linespoints

#set table "plot.tex"
#plot "fort.7"
#unset table

pause -1