package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorAdapter extends ThirdPartyDoor implements Door {
    private String code = DEFAULT_CODE;
    private DoorState doorState = DoorState.CLOSED;
    private LockStatus lockStatus = LockStatus.LOCKED;
    
    @Override
    public void open(String code) throws IncorrectDoorCodeException{
        try {
            unlock(code);
        } catch (CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        }
        try {
            setState(DoorState.OPEN);
        }
        catch (Throwable e) {};
    }

    @Override
    public void close(){
        try {
            setState(DoorState.CLOSED);
        }
        catch (Throwable e) {};
        lock();
    }

    @Override
    public boolean isOpen() {
        if (getState() == DoorState.OPEN) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation) 
            throws IncorrectDoorCodeException, CodeMismatchException {
        if (getLockStatus() == LockStatus.LOCKED){
            try {
                unlock(oldCode);
            } catch (CannotUnlockDoorException e) {
                throw new IncorrectDoorCodeException();
            }
        }
        if (newCode.equals(newCodeConfirmation) == false){
            throw new CodeMismatchException();
        }
        this.code = newCode;
    }

    @Override
    public boolean testCode(String code) {
        return code.equals(this.code);
    }
}


