package mvc;

abstract public class Model extends Bean{
    Boolean unsavedChanges = false;
    String fileName = null;
    public boolean getUnsavedChanges() {

        return unsavedChanges;
    }

    public void changed(){
        unsavedChanges = true;
        firePropertyChange(fileName, null, this);
    }

    public void setFileName(String fName) {
        fileName = fName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setUnsavedChanges(boolean b) {
        unsavedChanges = b;
    }
}
