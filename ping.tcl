# This script is created by NSG2 beta1
# <http://wushoupong.googlepages.com/nsg>

#===================================
#     Simulation parameters setup
#===================================
set val(stop)   10.0                         ;# time of simulation end

#===================================
#        Initialization        
#===================================
#Create a ns simulator
set ns [new Simulator]

#Open the NS trace file
set tracefile [open 2.tr w]
$ns trace-all $tracefile

#Open the NAM trace file
set namfile [open 2.nam w]
$ns namtrace-all $namfile

#===================================
#        Nodes Definition        
#===================================
#Create 6 nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

$ns color 1 Red
$ns color 2 Blue 

#===================================
#        Links Definition        
#===================================
#Createlinks between nodes
$ns duplex-link $n0 $n1 100.0Mb 10ms DropTail
$ns duplex-link $n1 $n2 50.0Mb 10ms DropTail
$ns duplex-link $n2 $n3 1.0Mb 10ms DropTail
$ns duplex-link $n3 $n4 1.0Mb 10ms DropTail
$ns duplex-link $n4 $n5 10.0Mb 10ms DropTail

$ns queue-limit $n0 $n1 4
$ns queue-limit $n1 $n2 4
$ns queue-limit $n2 $n3 5

#Give node position (for NAM)
$ns duplex-link-op $n0 $n1 orient right
$ns duplex-link-op $n1 $n2 orient right
$ns duplex-link-op $n2 $n3 orient right
$ns duplex-link-op $n3 $n4 orient right
$ns duplex-link-op $n4 $n5 orient right

Agent/Ping instproc recv {from rtt} {
$self instvar node_
puts "Node [$node_ id] recieved responses from $from with RTT=$rtt ms"
}
#===================================
#        Agents Definition        
#===================================
#Setup a TCP connection
set p0 [new Agent/Ping]
$p0 set packetSize_ 50000
$p0 set interval_ 0.0001
$ns attach-agent $n0 $p0
$p0 set fid_ 1

set p5 [new Agent/Ping]
$ns attach-agent $n5 $p5
$p5 set packetSize_ 30000
$p5 set interval_ 0.0001
$p5 set fid_ 2

$ns connect $p0 $p5

$ns at 0.1 "$p0 send"
$ns at 0.2 "$p0 send"
$ns at 0.3 "$p0 send"
$ns at 0.4 "$p0 send"

$ns at 0.1 "$p5 send"
$ns at 0.2 "$p5 send"
$ns at 0.3 "$p5 send"
$ns at 0.4 "$p5 send"

#===================================
#        Applications Definition        
#===================================

#===================================
#        Termination        
#===================================
#Define a 'finish' procedure
proc finish {} {
    global ns tracefile namfile
    $ns flush-trace
    close $tracefile
    close $namfile
    exec nam 2.nam &
    exit 0
}
$ns at $val(stop) "$ns nam-end-wireless $val(stop)"
$ns at $val(stop) "finish"
$ns at $val(stop) "puts \"done\" ; $ns halt"
$ns run
