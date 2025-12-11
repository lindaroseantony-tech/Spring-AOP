Technologies used:
1. 'AspectJ' is the library used to implement AOP in this project
2. Spring Data JPA
3. MYSQL Database
4. Lombok library

Programming Terms learned around Aspect   
1. Aspect-A modularization of a concern that cuts across multiple classes.
2. Joint Point-A point in the execution of a program where a advice can be applied.
3. Advice-The Action taken by the a Aspect at a particular joint point.
4. Point Cut-The expression defining where aspect should be applied.
5. Weaving-The process of applying aspects to target object. Spring AOP uses run time weaving htrough dynamic proxies.
6. AOP proxy-It is a dynamically created object that represents the target.

   Types of Advice
   1. Before-@Before annotation is used to implement the before advice. AOP advice @Before runs before a matched method is executed.
   2. After-@After annotation is used to call after a method is executed.
   3. AfterThrowing-@AfterThrowing annotation runs after a method throws an exception.
   4. AfterReturning-@AfterReturning annotation runs after a method is succesfully executed without throwing any exception.
   5. Around-@Around annotation is used for performance monitoring in AOP.
  
      Programming To do
      1. Use annotation @Aspect to the class implementing.
      2. Add annotation @Component so that spring will generate object for the class.
      3. create a logger field, 'private static final Logger LOGGER= LoggerFactory.getLogger(classname.class);
      4. create a method. below shows for Before Advice
        @Before("execution(* com.linda.springbootrest.service.JobService.getAllJobs(..)) || execution(* com.linda.springbootrest.service.JobService.getJob(..))")
    public void LogMethodCall(JoinPoint jp){
        LOGGER.info("Method called "+jp.getSignature().getName());
  
    }
   for After Advice
   @After("execution(* com.linda.springbootrest.service.JobService.getAllJobs(..)) || execution(* com.linda.springbootrest.service.JobService.getJob(..))")
    public void LogMethodExecuted(JoinPoint jp){
        LOGGER.info("Method executed "+jp.getSignature().getName());
    }

    @AfterThrowing("execution(* com.linda.springbootrest.service.JobService.getAllJobs(..)) || execution(* com.linda.springbootrest.service.JobService.getJob(..))")
    public void LogMethodErrorWhileExecution(JoinPoint jp){
        LOGGER.info("Method threw error "+jp.getSignature().getName());
    }

    @AfterReturning("execution(* com.linda.springbootrest.service.JobService.getAllJobs(..)) || execution(* com.linda.springbootrest.service.JobService.getJob(..))")
    public void LogMethodSucessfullyExecuted(JoinPoint jp){
        LOGGER.info("Method executed successfully "+jp.getSignature().getName());
    }
   
   for around advice-usecase-when a url is wrong , correct the url
   @Around("execution(* com.linda.springbootrest.service.JobService.getJob(..)) &&  args(postId)")
    public Object validateInput(ProceedingJoinPoint pjp, int postId) throws Throwable {
        if(postId<0){
            LOGGER.info("Negative JobId value");
            postId=-postId;
            LOGGER.info("Post ID value is changed");
        }
        Object obj=pjp.proceed(new Object[]{postId});
        return obj;
    }
