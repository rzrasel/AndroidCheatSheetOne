|------------------------------------------------------------|
public boolean isInteger(double argNumber)
{
	if(argNumber % 1 == 0)
	{
		return true;
	}
	return false;
}
public boolean isInteger_001(double argNumber)
{
	if((int) argNumber == argNumber)
	{
		return true;
	}
	return false;
}
|------------------------------------------------------------|
