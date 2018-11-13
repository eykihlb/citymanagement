package main.java.com.mydao.utils;

import main.java.com.mydao.model.FTPConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.SocketException;

@Component
public class FTPUtil {

    private final static Log logger = LogFactory.getLog(FTPUtil.class);

    /**
     * 获取FTPClient对象
     *
     * @param ftpHost
     *            FTP主机服务器
     * @param ftpPassword
     *            FTP 登录密码
     * @param ftpUserName
     *            FTP登录用户名
     * @param ftpPort
     *            FTP端口 默认为21
     * @return FTPClient
     */
    private static FTPClient getFTPClient(String ftpHost, String ftpUserName,
                                         String ftpPassword, int ftpPort) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                logger.info("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                logger.info("FTP连接成功。");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            logger.info("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }

    /**
     * 从FTP服务器下载文件
     *
     * @param fTPConfig FTP配置
     *
     * @param fileName 文件名称
     *
     * @return 下载文件本地完整路径
     */
    public static String downloadFtpFile(FTPConfig fTPConfig, String fileName) {
        String ftpHost = fTPConfig.getFtpHost();
        String ftpUserName = fTPConfig.getFtpUserName();
        String ftpPassword = fTPConfig.getFtpPassword();
        Integer ftpPort = Integer.parseInt(fTPConfig.getFtpPort());
        String ftpPath = fTPConfig.getFtpPath();
        String localPath = fTPConfig.getLocalPath();
        try {
            FTPClient ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);

            File localFiles = new File(localPath + File.separatorChar + fileName);
            OutputStream os = new FileOutputStream(localFiles);

            FTPFile[] ftpFiles = ftpClient.listFiles();
            for(FTPFile file : ftpFiles){
                if(fileName.equalsIgnoreCase(file.getName())){
                    File localFile = new File(localPath + "/" + file.getName());
                    os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }

            os.close();
            ftpClient.logout();

        } catch (FileNotFoundException e) {
            logger.error("没有找到" + ftpPath + "文件");
            e.printStackTrace();
        } catch (SocketException e) {
            logger.error("连接FTP失败.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件读取错误。");
            e.printStackTrace();
        }
        return localPath + "\\" + fileName;

    }

    /**
     * Description: 向FTP服务器上传文件
     * @param fTPConfig FTP配置
     * @param fileName ftp文件名称
     * @param input 文件流
     * @return 成功返回true，否则返回false
     */
    public static String uploadFile(FTPConfig fTPConfig,String fileName,InputStream input) {
        String success = "0";
        FTPClient ftpClient = new FTPClient();
        String ftpHost = fTPConfig.getFtpHost();
        String ftpUserName = fTPConfig.getFtpUserName();
        String ftpPassword = fTPConfig.getFtpPassword();
        Integer ftpPort = Integer.parseInt(fTPConfig.getFtpPort());
        String httpHost = fTPConfig.getHttpHost();
        String httpPort = fTPConfig.getHttpPort();
        String httpPath = fTPConfig.getHttpPath();
        String ftpPath = fTPConfig.getFtpPath();
        try {
            int reply;
            ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return "0";
            }
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);
            ftpClient.enterLocalPassiveMode();
            System.out.println(ftpClient.storeFile(fileName, input));

            input.close();
            ftpClient.logout();
            success = "http://"+httpHost+":"+httpPort+httpPath+"/"+fileName;
        } catch (IOException e) {
            logger.error("文件读取错误。");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                    logger.error("链接关闭错误。");
                    ioe.printStackTrace();
                }
            }
        }
        return success;
    }
}
