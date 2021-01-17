BEGIN {
}
{
if($6=="cwnd_")
printf("%f %f\n",$1,$7);
}
END {
}
