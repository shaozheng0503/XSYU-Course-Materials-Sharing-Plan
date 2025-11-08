.data
A:.word 3,6,12,-10,25,90,101,-34,-100,26
max: .word 
min:.word
.text
lw $1,0($0) #max=A[0]
lw $2,0($0) #min=A[0]
addi $3,$0,36 #lenth
addi $4,$0,0 #i
for: addi $4,$4,4 #i=i+1
lw $5, 0($4) #$5=A[i]
slt $6,$1,$5 #if(A[i]>max)$6=1
beq $0,$6,nextA #if($6==0)goto nextA
add $1,$5,$0 #max=A[i]
nextA: slt $6,$5,$2 #if(min>A[i])$6=1
beq $0,$6,nextB #if($6==0)goto nextB
add $2,$5,$0 #min=A[i]
nextB: beq $4,$3,nextC #if($4==$3)goto nextC
jal for
nextC: sw $1,40($0) #max¥Ê»ÎM[11]
sw $2,44($0) #min¥Ê»ÎM[12]
end: jal end #Ω· ¯
