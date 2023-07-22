package in.NoobCoders.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import in.NoobCoders.dao.DaOException;

@Aspect
@Component
public class MyCustomAspect 
{
	@Before("execution(* in.NoobCoders.dao.ProductDao.*(..))")
	public void logBefore(JoinPoint jp) {
		System.out.println("Before Executing "+jp.getSignature().getName());
		System.out.println("Arguments are :: "+Arrays.toString(jp.getArgs()));
	}
	
	@AfterThrowing(throwing = "t",pointcut = "execution(* in.NoobCoders..*Dao.*(..))")
	public void converToDaoException(Throwable t) throws DaOException{
		throw new DaOException(t);
	}
	
	
	//------------If PJP then it must  be annotated with @Around -------------
	@Around("execution(* in.NoobCoders.dao.ProductDao.get*(Double , Double))")
	public Object swapInputs(ProceedingJoinPoint pjp) throws Throwable 
	{
		Object[] args = pjp.getArgs();
		Double min = (Double) args[0];
		Double max = (Double) args[1];
		if(min > max) {
			args = new Object[] {max,min};
		}
		return pjp.proceed(args);
	}
}
