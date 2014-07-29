#!/bin/sh

filename='in.txt'
cat ${filename} | while read line
do
  type=`echo ${line} | cut -d " " -f 1`
  if [ "${type}" = "WS" ]; then
    java WSmodel ${line}
  elif [ "${type}" = "RND" ]; then
    java Rndgraph ${line}
  elif [ "${type}" = "BA" ]; then
    java BAModel ${line}
  fi
done

cat ${filename} | while read line
do
  java Degreecal ${line} >> results.csv
  java Triangle ${line} >> triangle.csv
done


ls *deg*.csv >> deg1.csv
filename1='deg1.csv'

cat ${filename1} | while read line
do
  gnuplot <<EOF
set style fill solid border lc rgb "black"
set xtics rotate by -90
set term pdf font "Arial"
set yrange[0:]
set title '$line'
set title font 'Arial,30'
set output "$line.pdf"
plot "$line" using 0:2:xtic(1) with boxes lw 2 lc rgb "light-cyan" notitle
quit
EOF
done

