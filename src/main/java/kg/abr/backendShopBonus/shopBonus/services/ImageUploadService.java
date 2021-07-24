package kg.abr.backendShopBonus.shopBonus.services;


import kg.abr.backendShopBonus.shopBonus.entity.enums.EStatus;
import kg.abr.backendShopBonus.shopBonus.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


@Service
@Slf4j
public class ImageUploadService {
    private final ImageRepository imageRepository;
    private final AdvertRepository advertRepository;
    private final CardRepository cardRepository;
    private final ProductAdvertisingRepository productAdvertisingRepository;
    private final ShoppingRepository shoppingRepository;
    private final StoreRepository storeRepository;


    public ImageUploadService(ImageRepository imageRepository,
                              AdvertRepository advertRepository,
                              CardRepository cardRepository,
                              ProductAdvertisingRepository productAdvertisingRepository,
                              ShoppingRepository shoppingRepository,
                              StoreRepository storeRepository) {
        this.imageRepository = imageRepository;
        this.advertRepository = advertRepository;
        this.cardRepository = cardRepository;
        this.productAdvertisingRepository = productAdvertisingRepository;
        this.shoppingRepository = shoppingRepository;
        this.storeRepository = storeRepository;
    }


    private byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            log.error("Cannot compress Bytes");
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }


    private static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException e) {
            log.error("Cannot decompress Bytes");
        }
        return outputStream.toByteArray();
    }

}
