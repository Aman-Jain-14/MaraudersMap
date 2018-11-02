import math
import random
from random import randint

a = []
for i in range (0,40):
	y = randint(0,40)
	a.append(y)
a.sort()
n = len(a)	

count = 0
def measure(i):	
	global count
	count = count + 1
	return a[i]

start = 0
end = n-1
thresh = 40/(n-1)
print thresh
ele = n
m_s = measure (start)
m_m = 0
m_e = measure (end)
print m_e
factor = 0
while(1):
	mid = int((start+end)/2)
	if(ele%2!=0):
		ele = ele/2 + 1
	else:
		ele = ele/2
	
	if((m_e - m_s) <= thresh):
		print m_s
		print m_e
		break
	m_m = measure(mid)
	print ('m_m = ',m_m)
	print ('ele = ',ele)
	print int(math.ceil(((m_m - m_s)/float(ele))))

	if (int(math.ceil(((m_m - m_s)/float(ele)))) > thresh):
		start = mid + 1
		m_s = measure(start)
		print ('lol')
	else:
		end = mid
		m_e = m_m
		
print count