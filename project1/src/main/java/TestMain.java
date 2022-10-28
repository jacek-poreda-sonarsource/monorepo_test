package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashSet;
import java.util.Set;

public class TestMain {

  public static void main(String[] args) throws IOException {
//    System.out.println("Hel98lo world!");
    setPermissions("aa");
    AccessController.doPrivileged(new PrivilegedAction() {
      public Object run() {
// privileged code goes here, for example:
        System.loadLibrary("awt");
        return null;
// nothing to return
      }
    });
  }
  public static void setPermissions(String filePath) throws IOException {
    Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
    // user permission
    perms.add(PosixFilePermission.OWNER_READ);
    perms.add(PosixFilePermission.OWNER_WRITE);
    perms.add(PosixFilePermission.OWNER_EXECUTE);
    // group permissions
    perms.add(PosixFilePermission.GROUP_READ);
    perms.add(PosixFilePermission.GROUP_EXECUTE);
    // others permissions
    perms.add(PosixFilePermission.OTHERS_READ); // Sensitive
    perms.add(PosixFilePermission.OTHERS_WRITE); // Sensitive
    perms.add(PosixFilePermission.OTHERS_EXECUTE); // Sensitive

    Files.setPosixFilePermissions(Paths.get(filePath), perms);
  }

  public void setPermissionsUsingRuntimeExec(String filePath) throws IOException {
    Runtime.getRuntime().exec("chmod 777 file.json"); // Sensitive
  }

  public void setOthersPermissionsHardCoded(String filePath ) throws IOException {
    Files.setPosixFilePermissions(Paths.get(filePath), PosixFilePermissions.fromString("rwxrwxrwx")); // Sensitive
  }


}
