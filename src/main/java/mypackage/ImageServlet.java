package mypackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "C:/webchat_uploads"; // Thư mục lưu ảnh bên ngoài

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String imageName = request.getParameter("name"); // Lấy tên file ảnh từ parameter

        if (imageName == null || imageName.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing image name parameter");
            return;
        }

        File imageFile = new File(UPLOAD_DIR, imageName);

        if (!imageFile.exists() || !imageFile.isFile()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found");
            return;
        }

        // Xác định Content Type (MIME type) dựa vào phần mở rộng file
        String contentType = getServletContext().getMimeType(imageFile.getAbsolutePath());
        if (contentType == null) {
            contentType = "application/octet-stream"; // Mặc định nếu không xác định được
        }
        response.setContentType(contentType);
        response.setContentLength((int) imageFile.length());

        // Đọc file ảnh và ghi vào response output stream
        try (FileInputStream in = new FileInputStream(imageFile);
                OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error reading image file");
        }
    }
}