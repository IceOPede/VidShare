package vidshare.demo;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import org.junit.Ignore;
import org.junit.Test;

public class test {

    private static final String ACCESS_TOKEN = "93paGsy2SdkAAAAAAAABQLklTHl9Ok_x0G7rFYH7thCYUqp0GX9ReYf6lEsZXdlv";

    @Test
    @Ignore
    public void test () throws DbxException {

        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        ListFolderResult result = client.files().listFolder("/static/Videos");

        for (Metadata metadata : result.getEntries()){
            System.out.println(client.files().getTemporaryLink(metadata.getPathLower()).getLink());
        }



    }
}
