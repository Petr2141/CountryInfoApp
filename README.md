# Country Info App

This project implements [the assigned task](HW-CODING_ASSIGNMENT.pdf).

### Required (Tested only on this version)

- **Android Studio Koala Feature Drop | 2024.1.2**  
  Build #AI-241.18034.62.2412.12266719, built on August 22, 2024  
  Runtime version: 17.0.11+0-17.0.11b1207.24-11852314 x86_64  
  VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.  
  macOS 13.1  
  GC: G1 Young Generation, G1 Old Generation  
  Memory: 2048M  
  Cores: 8  
  Metal Rendering is ON  

### How to Run

To clone and run the project, follow these steps:

```bash
git clone https://github.com/Petr2141/CountryInfoApp.git
```

In addition to the code itself, there are a number of other factors that contribute to the overall quality of the product:

| Status | Task                                                                                                                                                                              |
| ------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 🚀     | **[[Git_Flow_Strategy]]**: Implemented a branching strategy to manage feature development and releases.                                                                           |
| 🚀     | **Unit and Instrumented Tests**: Developed unit tests and instrumented tests to ensure code quality and functionality.                                                            |
| 🚀     | **Well-structured XML Layouts**: Created clean XML layouts with resources organized in separate files for maintainability and responsive design.                                  |
| 🚀     | **Support for Configuration Changes**: Implemented support for handling device rotation and other configuration changes gracefully.                                               |
| 🚀     | **Documentation**: Comprehensive documentation has been created (like this page) to assist users and developers.                                                                  |
| TODO   | **Add ROOM Database**: Plan to implement a ROOM database for caching and a local data source to enhance data management and offline capabilities.                                 |
| TODO   | **Detailed Code Comments**: Plan to add thorough comments throughout the code for better understanding and maintainability.                                                       |
| TODO   | **Support for Tablets and TVs**: Plan to enhance the app's design and functionality for tablet and TV compatibility.                                                              |
| TODO   | **Naming Conventions**: Plan to establish and follow consistent naming conventions throughout the codebase.                                                                       |
| TODO   | **Modularity**: Plan to refactor the project into multiple modules for better organization and scalability.                                                                       |
| TODO   | **Stability Monitoring and Event Analysis**: Plan to implement monitoring tools such as Crashlytics or Sentry to track app stability and user events.                             |
| TODO   | **CI/CD and Static Code Analysis**: Plan to set up Continuous Integration and Continuous Deployment (CI/CD) pipelines along with static code analysis for code quality assurance. |

### Stack
| Technology                | Purpose                                                      |
|---------------------------|--------------------------------------------------------------|
| Kotlin                    | Primary programming language for Android development         |
| Retrofit                  | Library for making network requests and handling APIs        |
| Flow                      | Asynchronous stream processing for reactive programming      |
| Kotlin Serialization       | For serializing and deserializing JSON data                 |
| JUnit                     | Framework for unit testing                                   |
| Espresso                  | Library for UI testing and automating user interactions     |
| Kotlin Coroutines         | Simplifies asynchronous programming and managing background tasks |
| View (XML)               | For designing user interfaces in Android applications        |


### Architecture
MVVM + clean architecture
![[Pasted image 20240917115216.png|350]]

| Layer  | Class                                             |
| ------ | ------------------------------------------------- |
| UI     | MainActivity<br>CountriesViewModel                |
| Domain | GetSortCountriesUseCase                           |
| Data   | CountriesRepository<br>CountriesNetworkDataSource |

### Dependency Injection

Since this is a minimal project scope, I chose not to use external libraries (such as Dagger, Hilt, etc.) and instead implemented manual Dependency Injection (DI).

| Class                    | Description                                                                                          |
| ------------------------ | ---------------------------------------------------------------------------------------------------- |
| **AppContainer**         | Contains dependencies for classes in the Data layer.                                                 |
| **CountriesApplication** | Provides access to the AppContainer, facilitating access to dependencies throughout the application. |

### About the Layout Design and Implementation

At first glance, this design may seem purely schematic and may not require exact reproduction. However, a closer inspection reveals a real challenge. To begin, I sketched out a few solutions on paper—this was one of the initial steps in visualizing the concept. Here's a screenshot of my notes:
![[scatch.jpg|400]]

In a real project, we would likely adhere to industry guidelines from Google: [Material Design](https://m3.material.io/). We would be particularly focused on responsiveness, expected user behavior, and what is commonly referred to as UX (User Experience).

However, in this case, we are implementing the design exactly as specified in the assignment:
![[Pasted image 20240923183525.png]]

During the implementation, I paid attention to several aspects:

| Aspect                 | Importance                                                                                     | Commit                                   |
|-----------------------|------------------------------------------------------------------------------------------------|------------------------------------------|
| Styles, Colors, Dimensions | For reusability, maintaining a consistent app style, and simplifying the implementation of a dark theme, etc. | e6c03efc2cf42b03744f6e1966f12d1c6204663a |
| Include / Merge       | For reusability                                                                                 | c0e1f6f90b9fa8b9b13c1bec18fbadbab872c2bd |
| String Resources      | For reusability and simplifying localization implementation                                      | 8336cac39324bc6ecea2cd2e8bc3ca3b4bd47a12 |

---
## Contact Information

If you have any questions or would like to get in touch, feel free to contact me:

- **GitHub**: [Petr2141](https://github.com/Petr2141)
- **Email**: [petr2141@gmail.com](mailto:petr2141@gmail.com)
- **LinkedIn**: [linkedin.com/in/peter-sulilo](https://www.linkedin.com/in/peter-sulilo)
- **CV**: [Peter Sulilo - Senior Software Engineer CV](Peter_Sulilo_CV_Senior_Software_Engineer.pdf)

I'm open to feedback, suggestions, or collaboration opportunities.
