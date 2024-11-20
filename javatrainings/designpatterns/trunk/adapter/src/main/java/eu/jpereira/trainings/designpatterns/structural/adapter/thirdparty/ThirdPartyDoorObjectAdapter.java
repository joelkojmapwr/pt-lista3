package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.ThirdPartyDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.ThirdPartyDoor.DoorState;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.ThirdPartyDoor.LockStatus;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorObjectAdapter implements Door {
    private ThirdPartyDoor thirdPartyDoor = new ThirdPartyDoor();
    
    @Override
    public void open(String code) throws IncorrectDoorCodeException{
        try {
            thirdPartyDoor.unlock(code);
        } catch (CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        }
        try {
            thirdPartyDoor.setState(DoorState.OPEN);
        }
        catch (Throwable e) {};
    }

    @Override
    public void close(){
        try {
            thirdPartyDoor.setState(DoorState.CLOSED);
        }
        catch (Throwable e) {};
        thirdPartyDoor.lock();
    }

    @Override
    public boolean isOpen() {
        return (thirdPartyDoor.getState() == DoorState.OPEN);
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation) 
            throws IncorrectDoorCodeException, CodeMismatchException {
        if (newCode.equals(newCodeConfirmation) == false){
            throw new CodeMismatchException();
        }
        try {
            thirdPartyDoor.unlock(oldCode);
            thirdPartyDoor.setNewLockCode(newCode);
        } catch (CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        }
        catch (Throwable e) {};
    }

    @Override
    public boolean testCode(String code) {
        LockStatus previousLockStatus = thirdPartyDoor.getLockStatus();
        boolean result = true;
        try {
            thirdPartyDoor.unlock(code);
        } catch (CannotUnlockDoorException e) {
            result = false;
        }
        if (previousLockStatus == LockStatus.LOCKED) {
            thirdPartyDoor.lock();
        }
        return result;
    }
}
