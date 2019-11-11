#!/usr/bin/gnuplot -persist
set xlabel "Step Size"
set logscale x 10
set ylabel "Error"
set logscale y 10
plot "fort.7" with linespoints

set table "plot.tex"
plot "fort.7"
unset table