package com.shakirov;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableCaching(proxyTargetClass = true)
@RequiredArgsConstructor
@Slf4j
public class ApplicationRunner /*implements CommandLineRunner */{

//    private final AsyncUserService asyncUserService;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationRunner.class, args);
        System.out.println();


//
//        try {
//            System.out.println("Method1");
//            try {
//                System.out.println("Method2");
//
//                try {
//                    System.out.println("Method3");
//                    new RuntimeException("Exception method 3");
//
//                } catch (RuntimeException e) {
//                    System.out.println(e);
//
//                }
//
//            } catch (RuntimeException e) {
//                System.out.println(e);
//
//            }
//
//        } catch (RuntimeException e) {
//            System.out.println(e);
//
//        }


//        UserService bean3 = context.getBean(UserService.class);
//        bean3.get();
//        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
//        System.out.println(context.getBean("pool1"));
//        System.out.println(context.getBean(DataBaseProperties.class));
//        System.out.println(context.getBean(DataBasePropertiesRecord.class));
//        AbstractApplicationContext abstractApplicationContext;
//
//        DispatcherServlet dispatcherServlet;
//        Catalina catalina;
//        int[] a = new int[10];

//        Object o = new Object();
//        Car bean = context.getBean(Car.class);
//        Car bean1 = context.getBean(Car.class);
//        Car bean2 = context.getBean(Car.class);
//        Map<String, String> map = new HashMap<>(32, 4);
//        map.put("1", "1");


//        ClassA classA = context.getBean(ClassA.class);
//        classA.getClassB();
//        ClassB classB = context.getBean(ClassB.class);
//        classB.getClassA();
//        System.out.println();
    }

//        @Bean
//    public CommandLineRunner CommandLineRunnerBean() {
//        return (args) -> {
//            System.out.println("******************Start CommandLineRunner");
//            AsyncUser userByIdSync = asyncUserService
////                    .getUserByIdAsync("1")
//                    .getUserByIdSync("1");
////                    .subscribe(user -> log.info("___________Get user async: {}", user));
//            log.info("******Get user sync: {}", userByIdSync);
//        };
//    }

//    @Override
//    public void run(final String... args) {
//        System.out.println("******************Start CommandLineRunner");
//        asyncUserService
//                .getUserByIdAsync("1")
//                .subscribe(user -> log.info("___________Get user async: {}", user));
//    }




/*    public static void main(String[] args) {

        // Метод obj1. isAssignableFrom(obj2) проверяет, находится ли obj2 в одной иерархии наследования с obj1
        // Таким образом Spring определяет, относится ли какой либо Bean Definition объектом типа
        // BeanFactoryPostProcessor, применяет он Reflection
        // BeanFactoryPostProcessor - нужен для первичной обработки Bean Definition
*//*        String value = "Hello";
        System.out.println(CharSequence.class.isAssignableFrom(value.getClass()));
        Number i = 10;
        System.out.println(Integer.class.isAssignableFrom(i.getClass()));*//*

        // Реализация контейнера
*//*        Container container = new Container();


//        ConnectionPool connectionPool = new ConnectionPool();
//        UserRepository userRepository = new UserRepository(connectionPool);
//        CompanyRepository companyRepository = new CompanyRepository(connectionPool);
//        UserService userService = new UserService(userRepository, companyRepository);

        ConnectionPool connectionPool = container.get(ConnectionPool.class);
        UserRepository userRepository = container.get(UserRepository.class);
        CompanyRepository companyRepository = container.get(CompanyRepository.class);
        UserService userService = container.get(UserService.class);*//*

        try (*//*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml")*//*
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {



            ConnectionPool pool1 = context.getBean("pool1", ConnectionPool.class);
//        ConnectionPool pool2 = context.getBean("pl2", ConnectionPool.class);
            System.out.println(pool1);

            CompanyRepository company = context.getBean("company", CompanyRepository.class);
            System.out.println(company);
            System.out.println("_____________________________");
*//*            PropertySourcesPlaceholderConfigurer bean = context.getBean(PropertySourcesPlaceholderConfigurer.class);
            bean.getAppliedPropertySources().stream()
                    .map(propertySource -> propertySource.getName())
                    .forEach(System.out::println);*//*

            CrudRepository crudRepository = context.getBean(CrudRepository.class);
            System.out.println(crudRepository.findById(1));


            System.out.println(crudRepository.findById(2));
            System.out.println(crudRepository.findById(3));


*//*            System.out.println("Test annotation_________");
            TestAnnotationClass testAnnotationClass = context.getBean(TestAnnotationClass.class);
            testAnnotationClass.sayMessage();
            System.out.println("____________________");*//*


            System.out.println("_________Go destroy");

            UserRepository userRepository = context.getBean("userRepository2", UserRepository.class);
            UserService userService = context.getBean(UserService.class);

            CompanyService companyService = context.getBean(CompanyService.class);
            System.out.println(companyService.findById(1));

        }

    }*/
}


/*
//        Integer[] i = new Integer[]{1, 2, 4, 5, 7, 9, 0};
//        Integer[] x = new Integer[]{2, 3, 5, 6, 7, 8, 0};


        List<Integer> list1 = Arrays.asList(1, 2, 4, 5, 7, 9, 0);
        List<Integer> list2 = Arrays.asList(2, 3, 5, 6, 7, 8, 0);
//        System.out.println(set_test_sergey1(list1, list2));

        BiConsumer<HashSet<Integer>, Integer> biConsumer =
                (s, item) -> {
                    if (s.contains(item)) {
                        s.remove(item);
                    } else {
                        s.add(item);
                    }
                };

        HashSet<Integer> collect = Stream.concat(Arrays.stream(i), Arrays.stream(x)).collect(
                HashSet::new, biConsumer,
                (s1, s2) -> s1.addAll(s2));
        collect.forEach(System.out::println);
	}

public static long set_test_sergey(List<Integer> list1, List<Integer> list2){
        Map<List<Integer>, List<Integer>> collect = Stream.concat(Stream.of(list1), Stream.of(list2)).parallel()
        .collect(Collectors.toMap(k -> k, v -> v, (x, y) -> null));

        collect.values().stream().flatMap(Collection::stream).forEach(System.out::print);
        return currentTimeMillis();
        }

/*    public static long set_test_sergey1(List<Integer> list1, List<Integer> list2) {
        List<Integer> res = (Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toMap(k -> k, v -> v, (x, y) -> null)))
                .entrySet().stream().filter(v -> v.getValue() == null).map(Map.Entry::getKey).toList();
        res.forEach(System.out::println);
        return currentTimeMillis();
    }*/

















