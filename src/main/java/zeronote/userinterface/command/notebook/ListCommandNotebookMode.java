package zeronote.userinterface.command.notebook;

import zeronote.exceptions.IncorrectAppModeException;
import zeronote.exceptions.InvalidCommandException;
import zeronote.notebooks.Notebook;
import zeronote.notebooks.NotebookShelf;
import zeronote.notebooks.Page;
import zeronote.notebooks.Section;
import zeronote.userinterface.AppState;
import zeronote.userinterface.command.CliCommand;

public class ListCommandNotebookMode extends CliCommand {
    public static final String COMMAND_WORD = "list";

    public ListCommandNotebookMode(String argument, AppState appState) {
        this.setAppState(appState);
        this.setCommandParams(argument);
        PRINTS_PERSONAL_MESSAGE = true;
    }

    private static void listBookshelf_nsp(NotebookShelf notebookShelf) {
        for (Notebook notebook : notebookShelf.getNotebooksArrayList()) {
            System.out.println("* " + notebook.getTitle());
            for (Section section : notebook.getSectionArrayList()) {
                System.out.println("  |-- " + section.getTitle());
                for (Page page : section.getPageArrayList()) {
                    System.out.println("        |-- " + page.getTitle());
                    System.out.println("            " + page.getContent());
                }
            }
        }
    }

    private static void listBookshelf_ns(NotebookShelf notebookShelf) {
        for (Notebook notebook : notebookShelf.getNotebooksArrayList()) {
            System.out.println("* " + notebook.getTitle());
            for (Section section : notebook.getSectionArrayList()) {
                System.out.println("  |-- " + section.getTitle());
            }
        }
    }

    private static void listBookshelf_n(NotebookShelf notebookShelf) {
        for (Notebook notebook : notebookShelf.getNotebooksArrayList()) {
            System.out.println("* " + notebook.getTitle());
        }
    }

    private static void listNotebook_sp(Notebook notebook) {
        for (Section section : notebook.getSectionArrayList()) {
            System.out.println("* " + section.getTitle());
            for (Page page : section.getPageArrayList()) {
                System.out.println("  |-- " + page.getTitle());
                System.out.println("        " + page.getContent());
            }
        }
    }

    private static void listNotebook_s(Notebook notebook) {
        for (Section section : notebook.getSectionArrayList()) {
            System.out.println("* " + section.getTitle());
        }
    }

    private static void listSection(Section section) {
        for (Page page : section.getPageArrayList()) {
            System.out.println("* " + page.getTitle());
            System.out.println("    " + page.getContent());
        }
    }

    @Override
    public void execute() throws IncorrectAppModeException, InvalidCommandException {
        switch (appState.getAppMode()) {
        case NOTEBOOK_SHELF:
            switch (commandParams) {
            case ("/s"):
                listBookshelf_ns(appState.getCurrentBookShelf());
                break;
            case ("/a"):
                listBookshelf_nsp(appState.getCurrentBookShelf());
                break;
            case(""):
                listBookshelf_n(appState.getCurrentBookShelf());
                break;
            default:
                throw new InvalidCommandException("There not exists such options");
            }
            break;
        case NOTEBOOK_BOOK:
            switch (commandParams) {
            case ("/a"):
                listNotebook_sp(appState.getCurrentNotebook());
                break;
            case(""):
                listNotebook_s(appState.getCurrentNotebook());
                break;
            default:
                throw new InvalidCommandException("There not exists such options");
            }
            break;
        case NOTEBOOK_SECTION:
            switch (commandParams) {
            case(""):
                listSection(appState.getCurrentSection());
                break;
            default:
                throw new InvalidCommandException("There not exists such options");
            }
            break;
        default:
            throw new IncorrectAppModeException();
        }
    }

}
