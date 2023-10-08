package com.mitocode.fullstack.service.impl;

import com.mitocode.fullstack.model.MediaFile;
import com.mitocode.fullstack.repo.IGenericRepo;
import com.mitocode.fullstack.repo.IMediaFileRepo;
import com.mitocode.fullstack.service.IMediaFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaFileServiceImpl extends CRUDImpl<MediaFile, Integer> implements IMediaFileService {
    private final IMediaFileRepo repo;
    @Override
    public IGenericRepo<MediaFile, Integer> getRepo() {
        return repo;
    }
}
