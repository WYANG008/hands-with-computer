### application context
Spring comes with several flavors of application context. Here are a few that you’ll
most likely encounter:
- AnnotationConfigApplicationContext—Loads a Spring application context
from one or more Java-based configuration classes
- AnnotationConfigWebApplicationContext—Loads a Spring web application
context from one or more Java-based configuration classes
- ClassPathXmlApplicationContext—Loads a context definition from one or
more XML files located in the classpath, treating context-definition files as classpath
resources
- FileSystemXmlApplicationContext—Loads a context definition from one or
more XML files in the filesystem
- XmlWebApplicationContext—Loads context definitions from one or more
XML files contained in a web application

The difference between using FileSystemXmlApplicationContext and ClassPathXml-
ApplicationContext is that FileSystemXmlApplicationContext looks for knight.xml
in a specific location within the filesystem, whereas ClassPathXmlApplicationContext
looks for knight.xml anywhere in the classpath (including JAR files).

Alternatively, if you’d rather load your application context from a Java configuration,
you can use AnnotationConfigApplicationContext:
ApplicationContext context = new AnnotationConfigApplicationContext(
com.springinaction.knights.config.KnightConfig.class);
Instead of specifying an XML file from which to load the Spring application context,
AnnotationConfigApplicationContext has been given a configuration class from
which to load beans.
With an application context in hand, you can retrieve beans from the Spring container
by calling the context’s getBean() method.