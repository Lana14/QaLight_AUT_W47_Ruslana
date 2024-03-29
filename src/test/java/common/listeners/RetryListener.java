package common.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
import common.utils.config.RetryAnalyzer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener implements IAnnotationTransformer {
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        Class<? extends IRetryAnalyzer> analyzer = annotation.getRetryAnalyzerClass();
        if (analyzer != null) {
            annotation.setRetryAnalyzer(RetryAnalyzer.class);
        }
    }
}
