OPENSSL 

1 创建公私钥证书

无密私钥

openssl genrsa -out n_prikey.pem 1024 

有密私钥，可以设置密码 

openssl genrsa -des3 -out y_prikey.pem 1024

公钥证书

openssl req -new -x509 -key n_prikey.pem -out n_pub.pem -days 3650

 一次性创建公私钥

openssl req -newkey rsa:2048 -nodes -keyout rsa_private.key -x509 -days 365 -out cert.pem

 截止目前pem是Base64编码的可以打开查看。

2格式转换

编码转换：DER<-->BASE64 
不同证书标准的转换：PKCS系列<-->PEM/CER 
PEM < -- > DER

openssl x509 -outform der -in n_pub.pem -out n_pub.der

Openssl x509 -inform der -in test.crt -out -outform PEM   certi.pem

 这样pub.cer打开是不可查看的。

其他常用证书格式转换

PEM --> PFX

Oopenssl pkcs12 -export -out certificate.pfx -inkey privateKey.key -in certificate.crt -certfile CACert.crt

PEM-->P12

openssl pkcs12 -export -out Cert.p12 -in Cert.pem -inkey key.pem

PEM-->X509

openssl x509 -in Key.pem -text -out Cert.pem

 PFX/P12 --> PEM

openssl pkcs12 -in certificate.pfx -out certificate.cer
openssl pkcs12 -in mycert.pfx -nocerts -nodes -out mycert.key

 如无需加密pem中私钥，可以添加选项-nodes；
 如无需导出私钥，可以添加选项-nokeys; 
