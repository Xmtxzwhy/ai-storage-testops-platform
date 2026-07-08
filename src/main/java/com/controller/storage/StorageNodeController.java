package com.controller.storage;

import com.entity.storage.StorageTestNodeEntity;
import com.service.storage.StorageTestNodeService;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/storage-nodes")
public class StorageNodeController {
    @Autowired
    private StorageTestNodeService storageTestNodeService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params) {
        PageUtils page = storageTestNodeService.queryPage(params);
        return R.ok().put("data", page);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        return R.ok().put("data", storageTestNodeService.selectById(id));
    }

    @RequestMapping("/save")
    public R save(@RequestBody StorageTestNodeEntity entity) {
        storageTestNodeService.insert(entity);
        return R.ok().put("data", entity.getId());
    }

    @RequestMapping("/update")
    public R update(@RequestBody StorageTestNodeEntity entity) {
        storageTestNodeService.updateById(entity);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        storageTestNodeService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
