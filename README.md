# web-e-commerce
This is an backend web Java application which will manage inventory and orders

To begin with,we begin to create a project to apply Spring Boot feautures.
Firstly we will go to start.spring.start.io and navigate this address in a brrowser.

Selecting in the project section as Maven project because we will use Maven dependecies to run this application.
In the second section , sure we will select Java language , then at the Spring Boot version we will choose the lastest version 
that is released but without (SNAPSHOT).According Project Metadata section, it helps us to make name of packages or projects more descriptive and more organized.Three last steps are : Packaging that will be Jar , the second one Java version that depends on IntelliJ idea version installed and the last one are the dependecies or libraries that we will need or help us 
to make this project functional and work . Here we can take dependecies as Spring Web for REST API architecture , MySqlDriver 
in my case to make possible the communication between MySql JDBC drivers and the environment that we are working, SpringData Jpa to persist data in SQL stores with Java Persistence API using Spring Data and Hibernate,Spring Security that by default has Basic Authentication implemented and is highly customizable authentication and access-control framework for Spring applications.Lombok dependecies are optional to take it, because it helps us to reduce boilerplate code with annotations 
written at the first lines of class.Another very useful dependecies is also ModelMapper class because it makes possible to convert Dto objects into Entity objects.

Now after completing these steps we are able to open this project on our IDE(IntelliJ Idea Community or Ultimate)
Then we after doing this step , now we are able to run our Spring Boot application and start to design our e-commerce application.

Now , for best practice and to make an organized project we start to divide into packages all the work that we will design.
Firstly we start with Dto or Entity that will contain useful information for objects that we will use. Dto packages serves us like request template or model that we will put in PostMan to test and send the requests of
REST API methods and at the end we will see what response we will take.Entity packages serves us only for Database properties like we have in Hibernate.With annotation @Entity used in every class of this package we create in MySql WorkBench each table with class names, but we will be careful for compound words .For example in Java we use camelCase format while in MySQL we will seperate with _ (underscore) between words.After these two packages we have another three very important to develop like : Service , Controller and Repository.
In the Service package we will implement all the bussines logic. At the Controller package we will perform dependcy injection between service classes to make possible to communicate as Beans and implementing base endpoints of an object and endpoints of their methods.In the Repository part we will create an Interface that implements JpaRepository .In this interface we can write queries with annotation and declare abstract methods to use in the service package like queries as well , but this interface brings us built-in methods to use.
The most important objects of an E -commerce are Products,Category,Client,Orders and OrderItems.Each of them will contain important information about the project.

All of these objects have IDs name or description , date created on and updated on .
For Products and Client is very simple to implement GET , POST , PUT or PATCH and DELETE methods because we don't have to make any calculations as we will do it with Orders and OrderItems.
According Orders , to make a complete Orders we need quantity of product that we will buy , price of it and the multiplication of quantity and price will be stored like subPrice or subTotal in OrderItems by totalPrice of the Order, because we will be able to buy more
than one product so we must add these subtotals and in the end produce the final price like an invoice.This logic I have implemented in OrderService class.
We will be careful to createOrder method because if we want to buy more products(quantity) that we have in the online shop it will raise an InputMismatchException .On other methods that we will implement we should be careful also by PaymentStatus because if the PaymentStatus is Payed we cannot remove or add an OrderItem.





