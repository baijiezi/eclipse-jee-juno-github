package foundation.others.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Serialization（序列化）是一种将对象以一连串的字节描述的过程；反序列化deserialization是一种将
 * 这些字节重建成一个对象的过程。Java序列化API提供一种处理对象序列化的标准机制。在这里你能学到
 * 如何序列化一个对象，什么时候需要序列化以及Java序列化的算法，我们用一个实例来示范序列化以后的字节
 * 是如何描述一个对象的信息的。
 * Java中，一切都是对象，在分布式环境中经常需要将Object从这一端网络或设备传递到另一端。
 * 这就需要有一种可以在两端传输数据的协议。Java序列化机制就是为了解决这个问题而产生。
 * 一个对象能够序列化的前提是实现Serializable接口，Serializable接口没有方法，更像是个标记。
 * 有了这个标记的Class就能被序列化机制处理。
 *
 */
public class SerialTest extends Parent implements Serializable 
{
	int version = 66;
	Contain con = new Contain();

	public static void main(String args[]) throws IOException 
	{
		//将SerialTest对象序列化并保存到文件
		FileOutputStream fos = new FileOutputStream("src/foundation/others/serialize/temp.out");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		SerialTest st = new SerialTest();
		oos.writeObject(st);
		oos.flush();
		oos.close();
		
		
		//将文件中已被序列化的对象进行反序列化，得到对象实例，输出对象的某个属性值
		try 
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/foundation/others/serialize/temp.out"));
			SerialTest st2 = (SerialTest)ois.readObject();
			System.out.println(st2.con.containVersion);
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public int getVersion() 
	{
		return version;
	}

}



/**
这个例子是相当的直白啦。SerialTest类实现了Parent超类，内部还持有一个Container对象。
序列化后的格式如下：
　　AC ED 00 05 73 72 00 0A 53 65 72 69 61 6C 54 65
　　73 74 05 52 81 5A AC 66 02 F6 02 00 02 49 00 07
　　76 65 72 73 69 6F 6E 4C 00 03 63 6F 6E 74 00 09
　　4C 63 6F 6E 74 61 69 6E 3B 78 72 00 06 70 61 72
　　65 6E 74 0E DB D2 BD 85 EE 63 7A 02 00 01 49 00
　　0D 70 61 72 65 6E 74 56 65 72 73 69 6F 6E 78 70
　　00 00 00 0A 00 00 00 42 73 72 00 07 63 6F 6E 74
　　61 69 6E FC BB E6 0E FB CB 60 C7 02 00 01 49 00
　　0E 63 6F 6E 74 61 69 6E 56 65 72 73 69 6F 6E 78
　　70 00 00 00 0B
我们来仔细看看这些字节都代表了啥。开头部分，见颜色：
AC ED: STREAM_MAGIC. 声明使用了序列化协议.
00 05: STREAM_VERSION. 序列化协议版本.
0x73: TC_OBJECT. 声明这是一个新的对象.  
   序列化算法的第一步就是输出对象相关类的描述。例子所示对象为SerialTest类实例，因此接下来输出SerialTest类的描述。见颜色：
0x72: TC_CLASSDESC. 声明这里开始一个新Class。
00 0A: Class名字的长度.
53 65 72 69 61 6c 54 65 73 74: SerialTest,Class类名.
05 52 81 5A AC 66 02 F6: SerialVersionUID, 序列化ID，如果没有指定，则会由算法随
机生成一个8byte的ID
0x02: 标记号. 该值声明该对象支持序列化。
00 02: 该类所包含的域个数。
接下来，算法输出其中的一个域，int version=66；见颜色：
0x49: 域类型. 49 代表"I", 也就是Int.
00 07: 域名字的长度.
76 65 72 73 69 6F 6E: version,域名字描述.
然后，算法输出下一个域，contain con = new contain();这个有点特殊，是个对象。描述
对象类型引用时需要使用JVM的标准对象签名表示法，见颜色：
0x4C: 域的类型.
00 03: 域名字长度.
63 6F 6E: 域名字描述，con
0x74: TC_STRING. 代表一个new String.用String来引用对象。
00 09: 该String长度.
4C 63 6F 6E 74 61 69 6E 3B: Lcontain;, JVM的标准对象签名表示法.
0x78: TC_ENDBLOCKDATA,对象数据块结束的标志
接下来算法就会输出超类也就是Parent类描述了，见颜色：
0x72: TC_CLASSDESC. 声明这个是个新类.
00 06: 类名长度.
70 61 72 65 6E 74: parent,类名描述。
0E DB D2 BD 85 EE 63 7A: SerialVersionUID, 序列化ID.
0x02: 标记号. 该值声明该对象支持序列化.
00 01: 类中域的个数.
   下一步，输出parent类的域描述，int parentVersion=100;同见颜色：
0x49: 域类型. 49 代表"I", 也就是Int.
00 0D: 域名字长度.
70 61 72 65 6E 74 56 65 72 73 69 6F 6E: parentVersion，域名字描述。
0x78: TC_ENDBLOCKDATA,对象块结束的标志。
0x70: TC_NULL, 说明没有其他超类的标志。.

   到此为止，算法已经对所有的类的描述都做了输出。下一步就是把实例对象的实际值输出了。这时候是从parent Class的域开始的，见颜色：
00 00 00 0A: 10, parentVersion域的值.   
还有SerialTest类的域：
00 00 00 42: 66, version域的值.
再往后的bytes比较有意思，算法需要描述contain类的信息，要记住，现在还没有对contain类进行过描述，见颜色：
0x73: TC_OBJECT, 声明这是一个新的对象.
0x72: TC_CLASSDESC声明这里开始一个新Class.
00 07: 类名的长度.
63 6F 6E 74 61 69 6E: contain,类名描述.
FC BB E6 0E FB CB 60 C7: SerialVersionUID, 序列化ID.
0x02: Various flags. 标记号. 该值声明该对象支持序列化
00 01: 类内的域个数。
    .输出contain的唯一的域描述，int containVersion=11；
0x49: 域类型. 49 代表"I", 也就是Int..
00 0E: 域名字长度.63 6F 6E 74 61 69 6E 56 65 72 73 69 6F 6E: containVersion, 域名字描述.
0x78: TC_ENDBLOCKDATA对象块结束的标志.
这时，序列化算法会检查contain是否有超类，如果有的话会接着输出。
0x70:TC_NULL，没有超类了。
最后，将contain类实际域值输出。
00 00 00 0B: 11, containVersion的值.
**/
