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
