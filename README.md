# Digital-Signature-Desktop-Aplication
Basic application to generate the private/public key in order to encrypt and decrypt media.
On the basis of these properties ,we can formulate the following reqirements for a         digital signature:
•	The signature must be a bit pattern that depends on the message of being signed.
•	The signature must use some information uniqe to sender ,to prevent both forgery and denial.
•	It must be relatively easy to produce the digital signature .
•	It must be relatively easy to recognize and verify the digital signature.
•	It must be computationally infeasible to forge a digital signature ,either by constructing  a new massage for an existing digital signature or by constructing a fraudulent digital signature for a given message.
•	 It must be practical to return a copy of the digital signature in storage.
A secure hash function ,embedded in a scheme such as that of figure satifies these reqirements.
THE ALGORITHM:
The digital signature algorithm is as follows:

GLOBAL PUBLIC-KEY COMPONENTS:
1.	p = a prime number, where 2L-1 < p < 2L for 512 = < L = <1024 and L a multiple of 64
2.  q = a prime divisor of p - 1, where 2159 < q < 2160 
3.  g = h(p-1)/q mod p, where h is any integer with 1 < h < p - 1 such that h(p-1)/q mod P>1
(g has order q mod p)
THE   USER’S  PRIVATE KEY:
x = a randomly or pseudorandomly generated integer with 0 < x < q
USER’S   PUBLIC  KEY:
 y = gx mod p 
USER’S PER-MESSAGE SECRET NUMBER:
 k = a randomly or pseudorandomly generated integer with 0 < k < q 

The integers p, q, and g can be public and can be common to a group of users. A user's private and public keys are x and y, respectively. They are normally fixed for a period of time. Parameters x and k are used for signature generation only, and must be kept secret. Parameter k must be regenerated for each signature. 

TO DEPLOY - 
1 - copy the code in netbeans and save it as java
2 - Run the code and cross verify the keys generated.
