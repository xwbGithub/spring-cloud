## 设计模式

| 范围 | 类模式 | 对象模式 |
| :-----: | :----: | :----: |
| 创建型模式 | 工厂 | 单例、原型、抽象工厂、建造者 |
| 结构型模式 | (类)适配器 | 代理、(对象）适配器、桥接<br>装饰、外观、享元、组合 |
| 行为型模式 | 模板方法、解释器 | 策略、命令、职责链、状态、备忘录<br>观察者、中介者、迭代器、访问者 |

![设计模式](designPattern.png)


## 七大设计原则核心思想
### <font color='green'>一、单一职责原则</font>
 单一职责原则（SRP：Single Responsibility principle）又称为单一功能原则
><font color='green'>核心</font>：解耦和增强内聚性（高内聚，低耦合）  
<font color='green'>描述</font> ：类被修改的几率很大，因此应该专注于单一的功能。如果你把多个功能放在同一个类中，功能之间就形成了关联，改变其中一个功能，有可能中止另一个功能，这时就需要新一轮的测试来避免可能出现的问题。
### <font color='green'>二、接口隔离原则</font>
接口隔离原则（ISP：Interface Segregation Principle）
> <font color='green'>核心</font>：客户端不应该依赖它不需要的接口；一个类对另一个类的依赖应该建立在最小的接口上。  
<font color='green'>描述</font>：提供给每个模块的都应该是单一接口，提供给几个模块就应该有几个接口，而不是建立一个庞大的臃肿的接口，容纳所有的客户端访问。接口是我们设计时对外提供的契约，通过分散定义多个接口，可以预防未来变更的扩散，提高系统的灵活性和可维护性。

### <font color='green'>三、依赖倒置原则</font>
依赖倒置原则（DIP：Dependency Inversion Principle）

><font color='green'>核心</font>：高层模块不应该依赖低层模块，二者都应该依赖其抽象；抽象不应该依赖细节，细节应该依赖抽象    
<font color='green'>描述</font>：依赖倒置的中心思想是面向接口编程；依赖倒置原则是基于这样的设计理念：相对于细节的多变性，抽象的东西要稳定的多。以抽象为基础搭建的架构比以细节为基础的架构要稳定的多。
在 java 中，抽象指的是接口或抽象类，细节就是具体的实现类。使用接口或抽象类的目的是制定好规范，而不涉及任何具体的操作，把展现细节的任务交给他们的实现类去完成。

### <font color='green'>四、开闭原则</font>
开闭原则（OCP：Open Closed Principle）

><font color='green'>核心</font>：软件实体应当对扩展开放，对修改关闭    
<font color='green'>描述</font>：开闭原则（Open Closed Principle）是编程中最基础、最重要的设计原则；
一个软件实体（如类、模块和方法），应该对扩展开放（对提供方），对修改关闭（对使用方）。用抽象构建框架，用实现扩展细节。
当软件需要变化时，尽量通过扩展软件实体的行为来实现变化，而不是通过修改已有的代码来实现。
编程中遵循其它原则，以及设计模式的目的就是遵循开闭原则。
### <font color='green'>五、里式替换原则</font>
里氏替换原则（LSP：Liskov Substitution Principle）

><font color='green'>核心</font> ：派生类（子类）对象可以在程式中代替其基类（超类）对象  
<font color='green'>描述</font>：在使用继承时，遵循里氏替换原则，在子类中尽量不要重写父类的方法
里氏替换原则告诉我们，继承实际上让两个类耦合性增强了，在适当的情况下，可以通过聚合，组合，依赖 来解决问题。
### <font color='green'>六、迪米特法则</font>

迪米特法则（LOD：Law of Demeter）又称为最少知道原则（The Least Knowledge Principle）

><font color='green'>核心</font>：一个对象应该对其他对象保持最少的了解  
<font color='green'>描述</font>：类与类关系越密切，耦合度越大
一个类对自己依赖的类知道的越少越好。也就是说，对于被依赖的类不管多么复杂，都尽量将逻辑封装在类的内部。对外除了提供的 public 方法，不对外泄露任何信息。
只与直接的朋友通信
直接朋友： 每个对象都会与其他对象有耦合关系，只要两个对象之间有耦合关系，我们就说这两个对象之间是朋友关系。耦合的方式很多，依赖，关联，组合，聚合等。其中，我们称出现成员变量，方法参数，方法返回值中的类为直接的朋友，而出现在局部变量中的类不是直接的朋友。也就是说，陌生的类最好不要以局部变量的形式出现在类的内部。
### <font color='green'>七、合成复用原则</font>
合成复用原则（CRP：Composite Reuse Principle）

><font color='green'>核心</font>：合成复用原则，要求在软件复用时，要先尽量使用组合或者聚合等关联关系实现，其次才考虑使用继承。  
<font color='green'>描述</font>：如果要使用继承关系，则必须严格遵循里氏替换原则。合成复用原则同里氏替换原则相辅相成的，两者都是开闭原则的具体实现规范。
通常类的复用分为继承复用和合成复用两种

继承复用的<font color='green'>缺点</font>：  
>1、继承复用破坏了类的封装性。因为继承会将父类的实现细节暴露给子类，父类对子类是透明的，所以这种复用又称为“白箱”复用。  
2、子类与父类的耦合度高。父类的实现的任何改变都会导致子类的实现发生变化，这不利于类的扩展与维护。  
3、它限制了复用的灵活性。从父类继承而来的实现是静态的，在编译时已经定义，所以在运行时不可能发生变化。  

合成复用的<font color='green'>优点</font>：  

>1、它维持了类的封装性。因为成分对象的内部细节是新对象看不见的，所以这种复用又称为“黑箱”复用。  
2、新旧类之间的耦合度低。这种复用所需的依赖较少，新对象存取成分对象的唯一方法是通过成分对象的接口。  
3、复用的灵活性高。这种复用可以在运行时动态进行，新对象可以动态地引用与成分对象类型相同的对象。  
## 单例模式
#### 1、<font color='red'>饿汉式(静态常量)</font>
```java
@Slf4j
public class SingletonTest01 {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        log.info("比对结果：{}",singleton==singleton2); //true
    }
}
/**
 * 饿汉式，(静态变量)
 */
class Singleton {
    //1、构造器私有化，外部new
    private Singleton() {
    }
    //本部内部类创建对象实例
    private final static Singleton instance = new Singleton();
    public static Singleton getInstance() {
        return instance;
    }
}
```

#### 2、<font color='red'>饿汉式(静态代码块)</font>
也可以使用，但是稍微有点内存的浪费(并且线程是安全的)
```java
@SuppressWarnings("All")
@Slf4j
public class SingletonTestStatic {
    public static void main(String[] args) {
        SingletonStatic singleton = SingletonStatic.getInstance();
        SingletonStatic singleton2 = SingletonStatic.getInstance();
        log.info("比对结果：{}",singleton == singleton2);
    }
}
/**
 * 饿汉式，(静态代码块)
 */
class SingletonStatic {
    /**
     * 1、构造器私有化，外部new
     */
    private SingletonStatic() {
        instance = new SingletonStatic();
    }
    //本部内部类创建对象实例
    private static SingletonStatic instance;
    public static SingletonStatic getInstance() {
        return instance;
    }
}
```
#### 3、 <font color='green'>~~懒汉式(<font color='red'>线程不安全</font>)~~</font>
<font color='red'>~~不推荐使用~~</font>
```java
@SuppressWarnings("All")
@Slf4j
public class SingletonHungryMan {
    public static void main(String[] args) {
        SingletonHungry singleton=SingletonHungry.getInstance();
        SingletonHungry singleton1=SingletonHungry.getInstance();
        log.info("比对结果：{}",singleton==singleton1);
        log.info("singleton:hasCode:{}",singleton.hashCode());
        log.info("singleton1:hasCode:{}",singleton1.hashCode());
    }
}
/**
 * 饿汉式，(静态代码块)
 */
class SingletonHungry {
    private static SingletonHungry instance;
    private SingletonHungry() {
    }
    //提供一个静态公有方法，当使用到该方法时候，才去创建instance
    //即懒汉式
    public static SingletonHungry getInstance() {
        if(null==instance){
            instance=new SingletonHungry();
        }
        return instance;
    }
}
```
#### 4、<font color='green'>懒汉式(线程安全，同步方法)</font>
```java
@SuppressWarnings("All")
@Slf4j
public class SingletonHungryManSync {
    public static void main(String[] args) {
        SingletonHungryManSync singleton= SingletonHungrySync.getInstance();
        SingletonHungryManSync singleton1= SingletonHungrySync.getInstance();
        log.info("比对结果：{}",singleton==singleton1);
        log.info("singleton:hasCode:{}",singleton.hashCode());
        log.info("singleton1:hasCode:{}",singleton1.hashCode());
    }
}
/**
 * 懒汉式，(静态代码块)
 */

class SingletonHungrySync {
    private static SingletonHungryManSync instance;
    private SingletonHungrySync() {
    }
    //提供一个静态的公有方法，同时加入同步处理代码synchronized， 解决线程安全问题
    public static synchronized SingletonHungryManSync getInstance() {
        if(null==instance){
            instance=new SingletonHungryManSync();
        }
        return instance;
    }
}
```
#### 5、<font color='green'>懒汉式(线程安全，<font color='red'>同步代码块(线程不安全)</font>)</font>
<font color='red'>~~不推荐使用~~</font>
```java
@Slf4j
public class SingletonHungryManSyncCode {
    public static void main(String[] args) {
        SingletonHungrySyncCode singleton = SingletonHungrySyncCode.getInstance();
        SingletonHungrySyncCode singleton1 = SingletonHungrySyncCode.getInstance();
        log.info("比对结果：{}", singleton == singleton1);
        log.info("singleton :hasCode:{}", singleton.hashCode());
        log.info("singleton1:hasCode:{}", singleton1.hashCode());
    }
}
class SingletonHungrySyncCode {
    private static SingletonHungrySyncCode instance;
    private SingletonHungrySyncCode() {
    }
    public static  SingletonHungrySyncCode getInstance() {
        if (null == instance) {
            //进入if再加锁，创建多个实例，不安全(不推荐使用)
            synchronized (SingletonHungrySyncCode.class) {
                instance = new SingletonHungrySyncCode();
            }
        }
        return instance;
    }
}

```
#### 6、<font color='red'>双重检查</font>  
<font color='red'>推荐使用</font>
```java
@Slf4j
public class SingletonDoubleCheckMain {
    public static void main(String[] args) {
        SingletonDoubleCheck singleton = SingletonDoubleCheck.getSingleton();
        SingletonDoubleCheck singleton2 = SingletonDoubleCheck.getSingleton();
        log.info("比对结果：{}", singleton == singleton2);
        log.info("singleton :hashCode：{}", singleton.hashCode());
        log.info("singleton2:hashCode：{}", singleton.hashCode());
    }
}
class SingletonDoubleCheck {
    private static volatile SingletonDoubleCheck singleton;
    private SingletonDoubleCheck() {
    }
    /**
     *  提供一个静态的公有方法，加入双重检查代码，解决线程安全问题，同时解决懒加载问题
     */
    public static synchronized SingletonDoubleCheck getSingleton() {
        if (null == singleton) {
            synchronized (SingletonDoubleCheck.class) {
                if (null == singleton) {
                    singleton = new SingletonDoubleCheck();
                }
            }
        }
        return singleton;
    }
}
```
#### 7、<font color='red'>静态内部类</font> 
<font color='red'>推荐使用</font>
```java
@SuppressWarnings("All")
@Slf4j
public class SingletonStaticInnerMain {
    public static void main(String[] args) {
        SingletonStaticInner singleton = SingletonStaticInner.getInstance();
        SingletonStaticInner singleton2 = SingletonStaticInner.getInstance();
        log.info("静态内部类完成单例模式");
        log.info("比对结果：{}", singleton == singleton2);
        log.info("singleton :hashCode：{}", singleton.hashCode());
        log.info("singleton2:hashCode：{}", singleton.hashCode());
    }
}
class SingletonStaticInner {
    private SingletonStaticInner() {
    }
    /**
     * 写一个静态内部类，该类中有一个静态属性SingletonStaticInner
     */
    private static class SingletonInstance {
        private static final SingletonStaticInner INSTANCE = new SingletonStaticInner();
    }
    /**
     * 提供一个静态的公有方法，直接返回SingletonInstance.INSTANCE;
     */
    public static SingletonStaticInner getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
```
#### 8、<font color='red'>枚举</font>  
```java
@Slf4j
public class SingletonEnumMain {
    public static void main(String[] args) {
        SingletonEnum singleton = SingletonEnum.INSTANCE;
        SingletonEnum singleton2 = SingletonEnum.INSTANCE;

        log.info("比对结果：{}", singleton == singleton2);
        log.info("singleton :hashCode：{}", singleton.hashCode());
        log.info("singleton2:hashCode：{}", singleton.hashCode());
    }
}
enum SingletonEnum {
    //属性
    INSTANCE;
}
```

总结、以上推荐使用的是：
<font color='red' size='4'>饿汉式，静态内部类，双重检查，枚举</font>
单例使用注意事项：
>   1、单例模式保证了系统内存中该类只存在一个对象，节省了系统资源，对于一些需要平凡创建校会的对象，使用单例模式可以提高系统性能  
    2、想实例化一个单例类的时候，必须要记住使用相应的获取对象的方法，而不是使用new  
    3、单例模式适用的场景：需要频繁的进行创建和销毁对象，创建对象时消耗过多或者耗费资源过多(即：重量级对象)，
> 但又经常用到的对象、工具类对象、频繁访问数据库或文件的对象(比如数据源、session工厂等)
## 工厂模式
### 简单工厂模式

![simpleFactory](simpleFactory.png)

+ 请看类： org.xwb.springcloud.factory.simple.PizzaStore

> 1、简单工厂模式是属于创建型模式，是工厂模式的一种，<font color='red'>简单工厂模式是由一个工厂对象决定创建出哪一种产品类的实力</font>。简单来工厂模式就是工厂模式家族中最简单最实用的模式  
2、简单工厂模式：定义了一个创建对象的类，有这个类来封装实例化对象的行为(代码)  
3、在软件开发中，当我们回用到大量的创建某种。某类或者谋批次对象时，就会使用工厂模式。  

![simpleFactory1](simpleFactory1.png)
```java
//简单工厂类
@Slf4j
public class SimplePizzaFactory {
    //根据orderType返回对应的pizza对象
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        log.info("\n使用简单工厂模式");
        if ("greek".equals(orderType)) {
            pizza = new GreekPizza();
            pizza.setName("希腊披萨");
        } else if ("chess".equals(orderType)) {
            pizza = new ChessPizza();
            pizza.setName("奶酪披萨");
        } else if ("pepper".equals(orderType)) {
            pizza = new ChessPizza();
            pizza.setName("胡椒披萨");
        }
        return pizza;
    }
}
//=====================================
@Slf4j
public class OrderPizzaSimpleFactory {
    public OrderPizzaSimpleFactory(SimplePizzaFactory simpleFactory){
        setSimpleFactory(simpleFactory);
    }
     // 定义一个简单工厂对象
    SimplePizzaFactory simpleFactory;
    Pizza pizza = null;
    public void setSimpleFactory(SimplePizzaFactory simpleFactory) {
        //用户输入的
        String orderType = "";
        this.simpleFactory = simpleFactory;
        do {
            orderType = getType();
            pizza = simpleFactory.createPizza(orderType);
            //订购成功
            if (null != pizza) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                //订购失败
                log.info("订购披萨失败！");
            }
        } while (true);
    }
    private String getType() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            log.info(" input pizza 种类{}", input);
            return input.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
//使用简单工厂
public class PizzaStore {
    public static void main(String[] args) {
        //简单工厂模式
        new OrderPizzaSimpleFactory(new SimplePizzaFactory());
    }
}
```

## 工厂方法模式
![factoryMethod](factoryMethod.png)
> 具体调用方法 org.xwb.springcloud.factory.method.PizzaStore
```java
@Slf4j
public abstract class Pizza {
    //名称
    protected String name;
    //准备原材料，不同的披萨不一样，因此。做成抽象类
    public abstract void prepare();

    public void bake() {
        log.info(name + " baking;");
    }
    public void cut() {
        log.info(name + " cutting;");
    }
    public void box() {
        log.info(name + " boxing;");
    }
    public void setName(String name) {
        this.name = name;
    }
}
@Slf4j
public class BJcheesePizza extends Pizza{
    @Override
    public void prepare() {
        setName("北京奶酪pizza");
        log.info("北京奶酪pizza 准备原材料");
    }
}
@Slf4j
public class BJPepperPizza extends Pizza{
    @Override
    public void prepare() {
        setName("北京胡椒pizza");
        log.info("北京胡椒pizza 准备原材料");
    }
}
@Slf4j
public class LDcheesePizza extends Pizza{
    @Override
    public void prepare() {
        setName("伦敦奶酪pizza");
        log.info("伦敦奶酪pizza 准备原材料");
    }
}
@Slf4j
public class LDPepperPizza extends Pizza{
    @Override
    public void prepare() {
        setName("伦敦胡椒pizza");
        log.info("伦敦胡椒pizza 准备原材料");
    }
}
//=================================================
//具体的订单工厂
public abstract class OrderPizza {

    //定义一个抽象方法，createPizza，让各个工厂子类自己实现
    abstract Pizza createPizza(String orderType);

    public OrderPizza(){
        Pizza pizza=null;
        String orderType;
        do {
            orderType= CommonUtils.getType();
            //抽象方法，有工厂子类完成
            pizza=createPizza(orderType);
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        }while (true);
    }
}
public class BJOrderPizza extends  OrderPizza{
    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza =null;
        if(orderType.equals("cheese")){
            pizza=new BJcheesePizza();
        }else if(orderType.equals("pepper")){
            pizza=new BJPepperPizza();
        }
        return pizza;
    }
}
public class LDOrderPizza extends OrderPizza {
    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new LDcheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
//下单
public class PizzaStore {
    public static void main(String[] args) {
        //new BJOrderPizza();
        new LDOrderPizza();
    }
}
```
### 抽象工厂模式
> 1、抽象工厂模式，定义一个inferface用于创建相关或有以来关系的对象簇，而无需知名具体的类  
> 2、抽象工厂模式可以将简单工厂模式和工厂方法模式进行整合
> 3、从涉及层面看，抽象工厂模式就是对简单工厂模式的该进(或者成为进一步的抽象)  
> 4、将工厂抽象为2层，abstractFactory(抽象工厂)和具体实现的工厂子类。程序员可以根据创建对象类型使用对应的工厂子类，这样将简单的工厂类会变成工厂簇，更利于代码的额维护和扩展
> 5、
