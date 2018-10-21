4SECURE CODE:
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;


public class createsignature extends javax.swing.JFrame
{
static int BUFFER_SIZE =1024;
static String zipArchieveName = "allfiles.zip";
    public createsignature()
    {
        super("4SECURE");
        initComponents();
    }
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 36));
        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText("DIGITAL SIGNATURE");

        jLabel3.setText("BROWSE ANY FILE:");

        jButton2.setText("Create Sign");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Verify Sign");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(93, 93, 93))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)))
                .addContainerGap(188, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel4)
                .addContainerGap(474, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(32, 32, 32)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(82, 82, 82)
                .addComponent(jLabel4)
                .addGap(142, 142, 142))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
File file;
String fi;
static KeyPairGenerator kpg;
static KeyPair kp;
byte[] mdbytes;
String ext,hash;
StringBuffer hexStringi = new StringBuffer();
byte[] dataBytes = new byte[1024];
JFileChooser jf;
char[] password = "mypassword".toCharArray();
byte[] baSalt = "'~7&03~/.".getBytes();
int nIterations = 1;

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
jf=new JFileChooser();
jf.showOpenDialog(this);
file=jf.getSelectedFile();
jTextField1.setText(file.getAbsolutePath());
fi=file.getAbsolutePath();   
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    try {
            FileInputStream fis = null;
            MessageDigest md = null;
            ext = extension();
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
            }
            int nread = 0;
            try {
                while ((nread = fis.read(dataBytes)) != -1) {
                    md.update(dataBytes, 0, nread);
                }
            } catch (IOException ex) {
                Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
            }
            mdbytes = md.digest();
            //convert the byte to hex format method of hash code
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < mdbytes.length; i++) {
                String hex = Integer.toHexString(0xff & mdbytes[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            hash = hexString.toString();
            key();
            encryptFile();
            try {
                zipcode();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);}
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
        }
}




void key()
    {
    byte[] cipherData;
try{
    PrivateKey pri=kp.getPrivate();
Cipher cipher = Cipher.getInstance("RSA");
cipher.init(Cipher.ENCRYPT_MODE, pri);
cipherData = cipher.doFinal(mdbytes);
FileOutputStream sigfos = new FileOutputStream("signature.sign");
sigfos.write(cipherData);
sigfos.close();
System.out.println("Digest(in hex format): " + hash);
jLabel4.setText("Signature created and stored in the file named as Signature.sign");
}
catch(Exception e)
{
System.out.println(e.getMessage());
}
    }//GEN-LAST:event_jButton2ActionPerformed



void zipcode() throws FileNotFoundException, IOException
  {
BufferedInputStream origin = null;
FileOutputStream dest = new FileOutputStream(zipArchieveName);
ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
byte[] data = new byte[BUFFER_SIZE];
List files = new ArrayList();
files.add("signature.sign");
files.add("encrypted."+ext);
for (Iterator i = files.iterator(); i.hasNext();) {
String filename = (String) i.next();
System.out.println("Adding: " + filename);
FileInputStream fin = new FileInputStream(filename);
origin = new BufferedInputStream(fin, BUFFER_SIZE);
ZipEntry entry = new ZipEntry(filename);
out.putNextEntry(entry);
int count;
while ((count = origin.read(data, 0, BUFFER_SIZE)) != -1) {
out.write(data, 0, count);
    }
origin.close();
      }
out.close();
}


void unzip()
    {
    try {
	ZipFile zipFile = new ZipFile("allfiles.zip");
        Enumeration<?> enu = zipFile.entries();
	while (enu.hasMoreElements()) {
	 ZipEntry zipEntry = (ZipEntry) enu.nextElement();
          String  name = zipEntry.getName();
	    long size = zipEntry.getSize();
	    long compressedSize = zipEntry.getCompressedSize();
	    System.out.printf("name: %-20s | size: %6d | compressed size: %6d\n",name, size, compressedSize);
            File file1 = new File(name);
	    if (name.endsWith("/")) {
		file1.mkdirs();
		continue;
	     }
            File parent = file1.getParentFile();
	if (parent != null)
        {
	parent.mkdirs();
	}
        InputStream is = zipFile.getInputStream(zipEntry);
	FileOutputStream fos = new FileOutputStream(file1);
	byte[] bytes = new byte[1024];
	int length;
	while ((length = is.read(bytes)) >= 0) {
	 fos.write(bytes, 0, length);
	}
	is.close();
	fos.close();
        }
	zipFile.close();
	}catch (IOException e) {
	    e.printStackTrace();
            }
}


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
        FileInputStream fis=null;
        MessageDigest md=null;
        byte[] mdbytes1;
             int flag=0;
            unzip();
            decryptFile();
            fis = new FileInputStream("decrypted."+ext);
            md = MessageDigest.getInstance("MD5");
            byte[] dataBytes = new byte[1024];
            int nread = 0;
            while ((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }
            mdbytes1 = md.digest();
            fis.close();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < mdbytes1.length; i++) {
                String hex = Integer.toHexString(0xff & mdbytes1[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            System.out.println("Digest(in hex format)created from sent file:  " + hexString.toString());
           generate();
    if(hexString.toString().equals(hexStringi.toString()))
                {
                    flag=1;
                }
                if(flag==0)
            jLabel4.setText("The data is incorrect");
                else
            jLabel4.setText("The data is correct");
        }
        catch (InvalidKeyException ex) {
            Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (IOException ex) {
            Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     void generate() throws FileNotFoundException, IOException
    {
        DataInputStream dis = null;
        try {
           File file1 = new File("signature.sign");
            int len = (int) file1.length();
            byte[] sig = new byte[len];
            dis = new DataInputStream(new FileInputStream(file1));
            dis.read(sig);
            dis.close();
            PublicKey publi=kp.getPublic();
            Cipher cipheri = Cipher.getInstance("RSA");
            cipheri.init(Cipher.DECRYPT_MODE,publi);
            byte[] cipherDatai = cipheri.doFinal(sig);
            String srco=new String(cipherDatai);
            for (int i=0;i<cipherDatai.length;i++) {
                String hex=Integer.toHexString(0xff & cipherDatai[i]);
   	     	if(hex.length()==1) hexStringi.append('0');
   	     	hexStringi.append(hex);}
    System.out.println("Digest(in hex format)from signature file: " + hexStringi.toString());
        }
        catch (IllegalBlockSizeException ex) {
            Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (InvalidKeyException ex) {
            Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
        }          catch (IOException ex) {
            Logger.getLogger(createsignature.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed
                       
     public void encryptFile() throws NoSuchAlgorithmException, FileNotFoundException, IOException
    {
File fileToEncrypt = new File(file.toString() );
System.out.println("encrytion file "+fileToEncrypt.toString());
File fileEncrypted = new File( "encrypted."+extension() );
        try {
                doFileOperation(fileToEncrypt, fileEncrypted, Cipher.ENCRYPT_MODE, password, baSalt, nIterations);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidAlgorithmParameterException ex) {
                Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
            }
        
 }

public void decryptFile() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchPaddingException, FileNotFoundException, IOException
    {
     File fileDecrypted = new File( "decrypted."+ext );
     File fileEncrypted = new File( "encrypted."+ext );
     doFileOperation( fileEncrypted,fileDecrypted,Cipher.DECRYPT_MODE,password,baSalt,nIterations );
   }

     public static void doFileOperation( File fileIn,File fileOut,int nMode,char[] password,byte[] baSalt,int nIterations ) throws InvalidKeyException,NoSuchAlgorithmException,InvalidKeySpecException,InvalidAlgorithmParameterException,NoSuchPaddingException,FileNotFoundException,IOException
  {
    Cipher cipher = createCipher( password, nMode, baSalt, nIterations );
    doCrypto( new FileInputStream( fileIn ),new FileOutputStream( fileOut ),cipher );
  }

     public static Cipher createCipher( char[] password,int nMode,byte[] baSalt,int nInterations ) throws NoSuchAlgorithmException,InvalidKeySpecException,InvalidKeyException,InvalidAlgorithmParameterException,NoSuchPaddingException
  {
    Cipher cipher = null;
    PBEParameterSpec pbeParamSpec = new PBEParameterSpec( baSalt, nInterations );
    PBEKeySpec pbeKeySpec = new PBEKeySpec( password );
    SecretKeyFactory keyFac = SecretKeyFactory.getInstance( "PBEWithSHA1AndDESede" );
    SecretKey key = keyFac.generateSecret( pbeKeySpec );
    cipher = Cipher.getInstance( "PBEWithSHA1AndDESede" );
    cipher.init( nMode, key, pbeParamSpec );
    return cipher;
  }

     public static void doCrypto( InputStream in, OutputStream out, Cipher cipher ) throws IOException
  {
    CipherOutputStream cos = new CipherOutputStream( out, cipher );
    byte[] baChunck = new byte[128];
    for( int nRead = 0; nRead >= 0; nRead = in.read( baChunck ) )
    {
      cos.write( baChunck, 0, nRead );
    }
    in.close();
    cos.close();
  }

     public String extension() {
         final String fullPath = file.toString();
         char extensionSeparator='.';

    int dot = fullPath.lastIndexOf(extensionSeparator);
    return fullPath.substring(dot + 1);
  }

    public static void main(String args[]) throws NoSuchAlgorithmException {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new createsignature().setVisible(true);}
        });
  kpg = KeyPairGenerator.getInstance("RSA");
  kpg.initialize(512); //initialize key pairs to 512 bits,can also take 1024 or 2048 bits
  kp = kpg.genKeyPair();
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
