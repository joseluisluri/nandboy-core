# Unit test convention

## Class naming
> [ClassNameTest]

## Unit naming

> [MethodName_StateUnderTest_ExpectedBehaviorTest]

### Why

1. **What do yo do when a method name change?**
A method is a entry point, rarely have to change. If they do, replace _MethodName_.
2. **Suffix Test is not too verbose?**
Yes, but JUnit requires this.


Examples:
```java
public void Sum_NegativeNumberAs1stParam_ExceptionThrownTest()
public void Sum_NegativeNumberAs1stParam_ReturnsEqualTo2Test()
public void Sum_NegativeNumberAs1stParam_ReturnsTrueTest()
```

## Variables naming

## Package structure

* test
	* package====
		* fixtures
		* MyClassTest.java
	* resources

## References
* Roy Osherove. 2005. Naming standards for unit tests. Available at: [http://osherove.com/blog/2005/4/3/naming-standards-for-unit-tests.html](http://osherove.com/blog/2005/4/3/naming-standards-for-unit-tests.html) [Accessed 13 February 2017]