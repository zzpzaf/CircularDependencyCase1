

# Spring: Avoid Circular Dependency error of your @Component(s)



![Spring-Circular-Dependency-Error1](https://github.com/zzpzaf/CircularDependencyCase1/assets/41330248/13bfd22c-fdbd-4626-9de8-27a192720def)
           

-------


This repo is the final outcome of my post [here](https://medium.com/@zzpzaf.se/spring-avoid-circular-dependency-error-of-your-component-s-66451d46b71a?sk=2e62d1773603fb4c74338b3a8cfa8924) or [here](https://www.devxperiences.com/pzwp1/2023/07/27/spring-avoid-circular-dependency-error-of-your-components/).

The post actually is a thorough step-by-step guide on how you can reproduce a circular dependency error in Spring, and then shows a couple of workarounds to solve the issue. It acually uses a custom inplementation of the “weird” [UsernamePasswordAuthenticationFilter](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/web/authentication/UsernamePasswordAuthenticationFilter.html), which, for its own instantiation, requires an instance of an [AuthenticationManager](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/authentication/AuthenticationManager.html) object.


### Clone the repo

Use the url of this [repo](https://github.com/zzpzaf/CircularDependencyCase1)(gh repo clone zzpzaf/CircularDependencyCase1): 

> gh repo clone zzpzaf/TokenUPAuthFilter_inMemory_final 

to clone it. 

(c) 2023 Panos Zafeiropoulos - https://www.devxperiences.com/
### License: [MIT](https://choosealicense.com/licenses/mit/)
