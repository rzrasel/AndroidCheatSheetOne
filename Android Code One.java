|------------------------------------------------------------|
public int getRandomInRange(int argMinVal, int argMaxVal) {
	if (argMinVal > argMaxVal) {
		int tempVal = argMaxVal;
		argMaxVal = argMinVal;
		argMinVal = tempVal;
	}
	Random random = new Random();
	int retVal = random.nextInt(argMaxVal - argMinVal + 1) + argMinVal;
	return retVal;
}
|------------------------------------------------------------|
public List<Integer> getNumberToDigits(int argNumber)
{
	List<Integer> digitList = new ArrayList<Integer>();
	int number, reminder;
	number = argNumber;
	while(number > 0)
	{
		reminder = number % 10;
		digitList.add(reminder);
		number = (number - reminder) / 10;
	}
	Collections.reverse(digitList);
	return digitList;
	/*
	for(int digit : digitList)
	{
		System.out.print(digit+", ");
	}
	for(int i = 0; i < digitList.length; i++)
	{
	}
	*/
}
|------------------------------------------------------------|
public boolean isIntNumber(double argNumber)
{
	if(argNumber % 1 == 0)
	{
		return true;
	}
	return false;
}
public boolean isIntNumber_001(double argNumber)
{
	if((int) argNumber == argNumber)
	{
		return true;
	}
	return false;
}
|------------------------------------------------------------|

|------------------------------------------------------------|
