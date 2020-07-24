1.1证书生成

//创建新的密钥对到密钥库中，回车后需要创建密钥库密码，以及私钥的密码

//密钥库中可以存储多条密钥对，alias是密钥对的别名，属于关键字，不允许重复

keytool -genkey -alias lhp -keyalg RSA -validity 365 -keysize 1024 -keystore ./lhp.keystore

//命令回车后，如下所示：

(Localhost)/home/db2inst2/keystore>keytool -genkey -alias lhp -keyalg RSA -validity 365 -keysize 1024 -keystore ./lhp.keystore



1.2公钥证书导出，导出的公钥无法查看，私钥不支持导出

keytool -export -alias test -file test.crt -keystore ./test.keystore



 1.3公钥证书导入，私钥不能导入

keytool -import -alias test2 -file test2.crt -keystore ./test.keystore



test2.crt是复制的test.crt



 证书导入别名已经存在，导入时会失败。

1.4证书查看

keytool -list -keystore test.keystore -rfc

 

Test2这个证书就是我们导入的证书了。

 

Test这个别名使我们的那一对公私玥，私钥是什么，这个是看不到的，条目类型是私钥对，这个与公钥的是不一样的。

1.5证书删除


keytool -delete -keystore test.keystore -alias test2



1.6Java代码实现


/**

    * @title  获取公钥

    * @Description

    * @author lingfei

    * @date 2018年3月2日

    * @param keyAlias

    * @return

    * @throws Exception

    */

    public static PublicKey getPublicKey(String keyAlias) throws Exception {

    String keyStoreFile = "test.keystore";

    String StoreFilePass = "123456";

    // 读取秘钥是所需要用到的工具类

    KeyStore ks;

    // 公钥类所对应的类

    PublicKey pubkey = null;

    // 得到实例对象

    ks = KeyStore.getInstance("JKS");

    FileInputStream fin;

    // 读取JKS文件

    fin = new FileInputStream(keyStoreFile);

    // 读取公钥

    ks.load(fin, StoreFilePass.toCharArray());

    Certificate cert = ks.getCertificate(keyAlias);

    pubkey = cert.getPublicKey();

    return pubkey;

    }

 

    /**

     * @title 获取私钥

     * @Description

     * @author lingfei

     * @date 2018年3月2日

     * @param keyAlias

     * @param keyAliasPass

     * @return

     * @throws Exception

     */

    public static PrivateKey getPrivateKey(String keyAlias, String keyAliasPass) throws Exception {

    String keyStoreFile = "test.keystore";

    String StoreFilePass = "123456";

    KeyStore ks;

    PrivateKey prikey = null;

    // 得到实例对象

    ks = KeyStore.getInstance("JKS");

    FileInputStream fin;

    // 读取JKS文件

    fin = new FileInputStream(keyStoreFile);

    // 读取私钥

    ks.load(fin, StoreFilePass.toCharArray());

    prikey = (PrivateKey)ks.getKey(keyAlias, keyAliasPass.toCharArray());

    return prikey;

    }

    

    /**

     * RSA签名<br>

     *

     * @param plain 源文本

     * @return 签名

     * @throws Exception

     */

    public static String sign(String plain) throws Exception {

    PrivateKey privateKey = getPrivateKey("test","123456");

        Signature instance = Signature.getInstance("SHA1WithRSA");

        instance.initSign(privateKey);

        instance.update(plain.getBytes(DEFAULT_CHARSET));

        return Base64Utils.encodeToString(instance.sign());

    }

 

 

    /**

     * RSA签名校验<br>

     * 对于<b>合作资方</b>，publicKey是指的公钥<br>

     * 对于，publicKey是指合作资方的公钥<br>

     *

     * @param plain 源文本

     * @param signature 签名

     * @param publicKey 公钥

     * @return 验证结果

     */

    public static boolean verify(String plain, String signature, String channelNo) throws Exception {

    PublicKey publicKey = getPublicKey("test");

        Signature instance = Signature.getInstance("SHA1WithRSA");

        instance.initVerify(publicKey);

        instance.update(plain.getBytes(DEFAULT_CHARSET));

        return instance.verify(Base64Utils.decodeFromString(signature));

    }

1.keystore--->pem


 // 注意 将wlpt.keystore转化为 p12 文件
// 导入成功的文件只能是 一对公私钥  其他证书将导入失败
keytool -importkeystore -srckeystore wlpt.keystore -destkeystore wlpt.keystore.p12 -srcstoretype JKS -deststoretype PKCS12
2// 将p12文件中的公私钥 到处private.pem 私钥导出
openssl pkcs12 -nocerts -nodes -in wlpt.keystore.p12 -out wlpt-private-key.pem

3// 公钥导出
keytool -export -alias hyjr -file wlpt-hyjr-pub.cer -keystore wlpt.keystore -storepass 123456
// 公钥转pem
openssl x509 -in wlpt-hyjr-pub.cer -inform DER -out wlpt-certificate-pub.pem -outform PEM


2.pem--->keystore
1、生成pkcs12格式的密钥文件：
$ openssl pkcs12 -export -in shcert.pem -inkey shkey.pem -out sh.pk12 -name shkey
(注：此过程中需要输入密码：123456)


2、生成keystore：
$ keytool -importkeystore -deststorepass 123456 -destkeypass 123456 -destkeystore sh.keystore -srckeystore sh.pk12 -srcstoretype PKCS12 -srcstorepass 123456 -alias shkey
————————————————
版权声明：本文为CSDN博主「Lynn_Meng」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/meng564764406/article/details/79427687