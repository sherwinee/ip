# Dash [🤖](https://emojipedia.org/robot)

> "The secret of getting ahead is getting started. The secret of getting started is breaking your complex overwhelming tasks into small manageable tasks, and then starting on the first one." — Mark Twain ([source](https://chatgpt.com/share/67b1b0e7-5e44-8004-ae43-6913b80a0b5a))

Dash is a **FREE** chatbot that helps you keep track of tasks and events when you're too busy to remember everything! Its:
- Typing based
- **Fast** and **Easy to Use**
- Uniquely Singaporean (_it speaks like one_)

To use it, just
1. Download it
2. Open it
3. Talk to it

## What it can do
- [ ] Keep track of Todos
- [ ] Keep track of Deadlines
- [ ] Keep track of Events
- [ ] Tag tasks

## Usage
`List` - Lists all tasks
`Search <name or #tag>` - Search for tasks containing the given string or the full tag.
`todo <Description> [#tags]` - Adds a Todo task
`deadline <Description> /by <Deadline YYYY-MM-DD> [#tags]` - Adds a Deadline task
`event <Description> /from <Start Date YYYY-MM-DD> /to <End Date YYYY-MM-DD> [#tags]` - Adds an Event
`delete <Index>` - Deletes task at given index
`deleteall `- Clears entire task list


Change the bot's name in the `Dash` class:
```java
public class Dash {
    public static final String botName = "Dash"; // Change this if you wish!

    private static final String filePath = "./data/dash.txt";
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;
    ...
```
